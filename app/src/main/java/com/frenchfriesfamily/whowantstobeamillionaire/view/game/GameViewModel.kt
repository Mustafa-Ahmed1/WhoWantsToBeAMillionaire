package com.frenchfriesfamily.whowantstobeamillionaire.view.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.model.data.StageDetails
import com.frenchfriesfamily.whowantstobeamillionaire.model.network.State
import com.frenchfriesfamily.whowantstobeamillionaire.model.repositories.StagesRepositoryImpl
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.GameResponse
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.Question
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Event
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.add
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.postEvent
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.AnswerState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class GameViewModel : BaseViewModel(), GameInteractionListener {

    private val stagesRepository = StagesRepositoryImpl()

    private val _questionsList = MutableLiveData<State<List<Question>?>>()
    val questionsList: LiveData<State<List<Question>?>> = _questionsList

    private val _question = MutableLiveData<Question?>()
    val question: LiveData<Question?> = _question

    private val _answers = MutableLiveData<List<String?>?>()
    val answers: LiveData<List<String?>?> = _answers

    private val _stage = MutableLiveData<StageDetails>()
    val stage: LiveData<StageDetails> = _stage

    private val _changeQuestion = MutableLiveData(true)
    val changeQuestion: LiveData<Boolean> = _changeQuestion

    private val _friendHelp = MutableLiveData(true)
    val friendHelp: LiveData<Boolean> = _friendHelp

    private val _audienceHelp = MutableLiveData(true)
    val audienceHelp: LiveData<Boolean> = _audienceHelp

    private val _seconds = MutableLiveData(15)
    val seconds: LiveData<Int> = _seconds


    private val _audienceClick = MutableLiveData<Event<Boolean>>()
    val audienceClick: LiveData<Event<Boolean>> = _audienceClick

    private val _callFriendClick = MutableLiveData<Event<Boolean>>()
    val callFriendClick: LiveData<Event<Boolean>> = _callFriendClick

    private val _okCLick = MutableLiveData<Event<Boolean>>()
    val okCLick: LiveData<Event<Boolean>> = _okCLick

    private val _exitClick = MutableLiveData<Event<Boolean>>()
    val exitCLick: LiveData<Event<Boolean>> = _exitClick

    private val _stayCLick = MutableLiveData<Event<Boolean>>()
    val stayCLick: LiveData<Event<Boolean>> = _stayCLick

    private val _leaveClick = MutableLiveData<Event<Boolean>>()
    val leaveClick: LiveData<Event<Boolean>> = _leaveClick


    fun onAudienceClicked() = _audienceClick.postEvent()
    fun onCallFriendClicked() = _callFriendClick.postEvent()
    fun onOkClicked() = _okCLick.postEvent()
    fun onExitCLicked() = _exitClick.postEvent()
    fun onStayClicked() = _stayCLick.postEvent()
    fun onLeaveClicked() = _leaveClick.postEvent()


    var questionCounter = 0
    var stageCounter = 0
    private var difficulty = 0


    init {
        getQuestions()
        setStage()
    }


    private fun getQuestions() {
        gameRepository.getQuestions(
            Constants.AMOUNT_OF_QUESTION,
            Constants.DIFFICULTY[difficulty],
            Constants.QUESTION_TYPE
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onGetQuestionSuccess, ::onGetQuestionError).add(disposable)
    }

    private fun onGetQuestionSuccess(response: State<GameResponse>?) {
        Log.i(QUESTIONS_TAG, "request ${difficulty + 1}: got ${response?.toData()?.results}")
        response?.toData()?.results?.apply {
            emitTimerSeconds()
            _questionsList.postValue(State.Success(this))
            this[questionCounter].let {
                _question.postValue(it)
                _answers.postValue(it.incorrectAnswers?.plus(it.correctAnswer)?.shuffled())
            }
        }
    }

    private fun setQuestion() {
        _questionsList.value?.toData()?.get(questionCounter).apply {
            _question.postValue(this)
            _answers.postValue(this?.incorrectAnswers?.plus(this.correctAnswer))
        }
    }

    private fun onGetQuestionError(throwable: Throwable) {}

    fun checkQuestionLevel() {
        emitTimerSeconds()
        when (stageCounter) {
            4 -> nextDifficulty()
            9 -> nextDifficulty()
            14 -> gameOver()
            else -> {
                questionCounter++
                setQuestion()
            }
        }
        stageCounter++
        setStage()
    }

    private fun nextDifficulty() {
        questionCounter = 0
        difficulty++
        getQuestions()
    }

    private fun gameOver() {}

    fun getStageList() = stagesRepository.getStages().reversed()

    private fun setStage() {
        val stageList = getStageList()
        _stage.postValue(stageList[stageCounter.plus(1)])
    }

    fun onChangeQuestion() {
        _changeQuestion.postValue(false)
        questionCounter++
        setQuestion()
    }


    fun onCallFriend(call: Boolean) = _friendHelp.postValue(call)
    fun onAskAudience(audience: Boolean) = _audienceHelp.postValue(audience)


    private fun emitTimerSeconds() {
        disposable.clear()
        val gameTimer = Observable.intervalRange(0, 16, 0, 1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        gameTimer.subscribe({
            _seconds.postValue(15 - it.toInt())
        }, {
            Log.e("Timer", "Error: ${it.message}")
        }, {

        }).add(this.disposable)
    }


    private val _answerState = MutableLiveData(AnswerState.IS_DEFAULT)
    val answerState: LiveData<AnswerState> = _answerState


    override fun onClickAnswer(answerText: String) {
        _answerState.value = AnswerState.IS_PRESSED

        Single.just(answerText).delay(2000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _ ->
                checkAnswerState(answerText)
            }
    }

    private fun checkAnswerState(answer: String) {
        if (answer == _question.value?.correctAnswer) {
            _answerState.postValue(AnswerState.IS_CORRECT)
            checkQuestionLevel()
        } else {
            _answerState.postValue(AnswerState.IS_WRONG)
            gameOver()
        }
    }

    companion object {
        const val QUESTIONS_TAG = "QUESTIONS_TAG"
        const val SECONDS_TAG = "SECONDS_TAG"
    }

}