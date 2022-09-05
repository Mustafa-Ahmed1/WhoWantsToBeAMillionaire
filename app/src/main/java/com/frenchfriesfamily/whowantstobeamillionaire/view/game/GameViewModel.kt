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
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.TimeDurations.ZERO
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.add
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.AnswerState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

//TODO: I don't think you can clean this mess easily, use Boy Scout Rule whenever you work
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

    private val _positionOfQuestion = MutableLiveData(0)
    val positionOfQuestion:LiveData<Int> =_positionOfQuestion

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
            .subscribe(::onGetQuestionSuccess,::onGetQuestionError).add(disposable)
    }

    private fun onGetQuestionSuccess(response: State<GameResponse>?) {
        Log.i(QUESTIONS_TAG, "request ${difficulty + 1}: got ${response?.toData()?.results}")
        response?.toData()?.results?.apply {
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

    private fun onGetQuestionError(throwable: Throwable) { }

    fun checkQuestionLevel() {
        emitTimerSeconds()
        when (stageCounter) {
            5 -> nextDifficulty()
            10 -> nextDifficulty()
            15 -> gameOver()
            else ->{ questionCounter++
                setQuestion() }
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
        _stage.postValue(stageList[stageCounter])
    }

    fun onChangeQuestion() {
        _changeQuestion.postValue(false)
        questionCounter++
        setQuestion()
    }


    fun onCallFriend(call: Boolean) {
        _friendHelp.postValue(call)
    }

    fun onAskAudience(audience: Boolean) {
        _audienceHelp.postValue(audience)
    }

    private fun emitTimerSeconds() {
        Observable.fromIterable(_seconds.value!! downTo ZERO)
            .zipWith(Observable.interval(1, TimeUnit.SECONDS)) { seconds, _ ->
                _seconds.postValue(seconds)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { Log.i(SECONDS_TAG, "Error: ${it.message}") }
            .subscribe()
            .add(disposable)
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