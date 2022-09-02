package com.frenchfriesfamily.whowantstobeamillionaire.view.question

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.model.AnswerState
import com.frenchfriesfamily.whowantstobeamillionaire.model.data.StageDetails
import com.frenchfriesfamily.whowantstobeamillionaire.model.network.State
import com.frenchfriesfamily.whowantstobeamillionaire.model.repositories.QuestionsRepository
import com.frenchfriesfamily.whowantstobeamillionaire.model.repositories.StagesRepository
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.QuestionResult
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.TimeDurations.MAX_DURATION
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.TimeDurations.ZERO
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.add
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit

class QuestionViewModel : BaseViewModel(), QuestionInteractionListener {

    private val questionsRepository = QuestionsRepository()
    private val stagesRepository = StagesRepository()
    private val disposable = CompositeDisposable()

    private val _questionsList = MutableLiveData<State<List<QuestionResult>?>>()
    val questionsList: LiveData<State<List<QuestionResult>?>> = _questionsList

    private val _question = MutableLiveData<QuestionResult?>()
    val question: LiveData<QuestionResult?> = _question

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
    val seconds: LiveData<Int>
        get() = _seconds

    private var questionCounter = 0
    private var stageCounter = 0
    private var difficulty = 0


    init {
        getQuestions()
        setQuestion()
        setStage()
    }

    private fun getQuestions() {
        questionsRepository.getQuestioneList(
            Constants.AMOUNT_OF_QUESTION,
            Constants.DIFFICULTY[difficulty],
            Constants.QUESTION_TYPE
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.i(QUESTIONS_TAG, "request ${difficulty + 1}: got ${it.toData()?.results}")
                if (it is State.Success) {
                    _questionsList.postValue(State.Success(it.toData()?.results))
                    _question.postValue(it.toData()?.results?.get(questionCounter))
                }
            }.add(disposable)
        setQuestion()
    }


    fun changeQuestion() {
        emitTimerSeconds()
        when (stageCounter) {
            4 -> nextDifficulty()
            9 -> nextDifficulty()
            14 -> gameOver()
            else -> questionCounter++
        }
        setQuestion()
        stageCounter++
        setStage()
    }

    private fun nextDifficulty() {
        questionCounter = 0
        difficulty++
        getQuestions()
    }

    private fun gameOver() {}

    private fun setQuestion() {
        val question = _questionsList.value?.toData()?.get(questionCounter)
        _question.postValue(question)
        _answers.postValue(question?.incorrectAnswers?.plus(question.correctAnswer))
    }

    private fun setStage() {
        val stageList = stagesRepository.getStages().reversed()
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
        val observable = Observable.fromIterable(_seconds.value!! downTo ZERO)
            .zipWith(Observable.interval(1, TimeUnit.SECONDS)) { seconds, _ ->
                _seconds.postValue(seconds)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { Log.i(SECONDS_TAG, "Error: ${it.message}") }.subscribe()

    }


    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }


    companion object {
        const val QUESTIONS_TAG = "QUESTIONS_TAG"
        const val SECONDS_TAG = "SECONDS_TAG"
    }


    private val _answerState = MutableLiveData<AnswerState>()
    val answerState: LiveData<AnswerState>
        get() = _answerState


    override fun onClickAnswer(answerText: String) {
        _answerState.value = AnswerState.IS_PRESSED

        Single.just(answerText).delay(3000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _ ->
                checkAnswerState(answerText)
                changeQuestion()
            }
    }

    private fun checkAnswerState(answer: String) {
        if (answer == _question.value?.correctAnswer) {
            _answerState.postValue(AnswerState.IS_CORRECT)
            changeQuestion()
        } else {
            _answerState.postValue(AnswerState.IS_WRONG)
            changeQuestion()
        }
    }

}