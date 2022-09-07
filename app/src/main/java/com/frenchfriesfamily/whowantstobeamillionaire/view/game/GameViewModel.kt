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
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.toAnswer
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.Answer
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.AnswerState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class GameViewModel : BaseViewModel(), GameInteractionListener {

    private val stagesRepository = StagesRepositoryImpl()

    private val timerDisposable = CompositeDisposable()

    private val _questionsList = MutableLiveData<State<List<Question>?>>()
    val questionsList: LiveData<State<List<Question>?>> = _questionsList

    private val _question = MutableLiveData<Question?>()
    val question: LiveData<Question?> = _question

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
    var stageCounter = 1
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
            .subscribe(::onGetQuestionSuccess, ::onGetQuestionError)
            .add(disposable)
    }

    private val _answers = MutableLiveData<List<Answer>?>()
    val answers: LiveData<List<Answer>?> = _answers

    private fun onGetQuestionSuccess(response: State<GameResponse>) {
        when (response) {
            is State.Loading -> _questionsList.postValue(response)
            is State.Success -> {
                _questionsList.value = State.Success(response.toData()?.results)
                emitTimerSeconds()
                setQuestion()
            }
            is State.Error -> _questionsList.postValue(State.Error(response.message))
        }
    }

    private fun onGetQuestionError(throwable: Throwable) {
        _questionsList.postValue(State.Error(throwable.message.toString()))
    }

    private fun setQuestion() {
        _questionsList.value?.toData()?.get(questionCounter).apply {
            _question.postValue(this)
            _answers.postValue(this?.toAnswer())
        }
    }

    fun checkQuestionLevel() {
        emitTimerSeconds()
        when (stageCounter) {
            5 -> nextDifficulty()
            10 -> nextDifficulty()
            15 -> gameOver()
            else -> {
                questionCounter++
                stageCounter++
                setStage()
                setQuestion()
            }
        }
    }

    private fun gameOver(){
        setStage()
        _gameOver.postValue(Event(true))
    }

    private fun nextDifficulty() {
        questionCounter = 0
        stageCounter++
        difficulty++
        setStage()
        getQuestions()
    }

    fun getStageList() = stagesRepository.getStages().reversed()

    private fun setStage() {
        val stageList = getStageList()
        _stage.postValue(stageList[stageCounter])
    }

    fun onChangeQuestion() {
        _changeQuestion.postValue(false)
        questionCounter++
        setQuestion()
    }


    fun onCallFriend(call: Boolean) = _friendHelp.postValue(call)
    fun onAskAudience(audience: Boolean) = _audienceHelp.postValue(audience)


    private fun emitTimerSeconds() {
        timerDisposable.clear()
        val gameTimer = Observable.intervalRange(0, 16, 0, 1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        gameTimer.subscribe({
            _seconds.postValue(15 - it.toInt())
        }, {
            Log.e("Timer", "Error: ${it.message}")
        }).add(timerDisposable)
    }

    override fun onClickAnswer(answerText: Answer) {
        _answers.value = _answers.value.apply { answerText.state = AnswerState.IS_PRESSED }
        Single.timer(2000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _ ->
                checkAnswerState(answerText)
            }
    }

    private val _gameOver = MutableLiveData<Event<Boolean>>()
    val gameOver: LiveData<Event<Boolean>> = _gameOver

    private fun checkAnswerState(answer: Answer){
        _answers.value = _answers.value.apply {
            if (answer.answer == _question.value?.correctAnswer) {
                answer.state = AnswerState.IS_CORRECT
                Single.timer(1000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { _ ->
                        checkQuestionLevel()
                    }
            }
            else {
                answer.state = AnswerState.IS_WRONG
                Single.timer(1000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { _ ->
                        stageCounter--
                        gameOver()
                    }
            }
        }
    }

    companion object {
        const val QUESTIONS_TAG = "QUESTIONS_TAG"
        const val SECONDS_TAG = "SECONDS_TAG"
    }
}