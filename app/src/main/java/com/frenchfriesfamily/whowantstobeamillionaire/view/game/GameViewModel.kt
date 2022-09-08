package com.frenchfriesfamily.whowantstobeamillionaire.view.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.model.data.StageDetails
import com.frenchfriesfamily.whowantstobeamillionaire.model.network.State
import com.frenchfriesfamily.whowantstobeamillionaire.model.repositories.StagesRepositoryImpl
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.GameResponse
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.Question
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Game.AMOUNT_OF_QUESTION
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Game.DIFFICULTY
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Game.QUESTION_TYPE
import com.frenchfriesfamily.whowantstobeamillionaire.utils.event.Event
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.add
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.postEvent
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.toAnswer
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.Answer
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.AnswerState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class GameViewModel : BaseViewModel(), GameInteractionListener {

    private val stagesRepository = StagesRepositoryImpl()

    private val timerDisposable = CompositeDisposable()

    private val _questionsList = MutableLiveData<State<List<Question>?>>()
    val questionsList: LiveData<State<List<Question>?>> = _questionsList

    private val _question = MutableLiveData<Question?>()
    val question: LiveData<Question?> = _question

    private val _stage = MutableLiveData<StageDetails>()
    val stage: LiveData<StageDetails> = _stage

    private val _answers = MutableLiveData<List<Answer>?>()
    val answers: LiveData<List<Answer>?> = _answers

    private val _changeQuestion = MutableLiveData(true)
    val changeQuestion: LiveData<Boolean> = _changeQuestion

    private val _friendHelp = MutableLiveData(true)
    val friendHelp: LiveData<Boolean> = _friendHelp

    private val _audienceHelp = MutableLiveData(true)
    val audienceHelp: LiveData<Boolean> = _audienceHelp

    private val _seconds = MutableLiveData(15)
    val seconds: LiveData<Int> = _seconds

    private val _gameOver = MutableLiveData<Event<Boolean>>()
    val gameOver: LiveData<Event<Boolean>> = _gameOver


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

    private val _stateTimer = MutableLiveData<Boolean>()
    val stateTimer : LiveData<Boolean> = _stateTimer


    val onAudienceClicked = fun() = _audienceClick.postEvent()
    val onCallFriendClicked = fun() = _callFriendClick.postEvent()
    val onOkClicked = fun() = _okCLick.postEvent()
    val onExitCLicked = fun() = _exitClick.postEvent()
    val onStayClicked = fun() = _stayCLick.postEvent()
    val onLeaveClicked = fun() = _leaveClick.postEvent()


    private var questionCounter = 0
    var stageCounter = 1
    private var difficulty = 0


    init {
        getQuestions()
        setStage()
    }


    fun getQuestions() {
        gameRepository.getQuestions(
            AMOUNT_OF_QUESTION,
            DIFFICULTY[difficulty],
            QUESTION_TYPE
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onGetQuestionSuccess, ::onGetQuestionError)
            .add(disposable)
    }

    private fun onGetQuestionSuccess(response: State<GameResponse>) {
        when (response) {
            is State.Loading -> _questionsList.postValue(response)
            is State.Success -> onStateSuccess(response)
            is State.Error -> onStateError(response.message)
        }
    }

    private fun onStateError(message: String) {
        timerDisposable.clear()
        _questionsList.postValue(State.Error(message))
    }

    private fun onStateSuccess(response: State<GameResponse>) {
        _questionsList.value = State.Success(response.toData()?.results)
        setQuestion()
    }

    private fun onGetQuestionError(throwable: Throwable) {
        _questionsList.postValue(State.Error(throwable.message.toString()))
    }

    private fun setQuestion() {
        emitTimerSeconds()
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

    private fun gameOver() {
        setStage()
        _gameOver.postEvent()
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

    val onChangeQuestion = fun() {
        _changeQuestion.postValue(false)
        questionCounter++
        setQuestion()
    }

    fun changeQuestion() = _changeQuestion.postValue(true)
    fun callFriend() = _friendHelp.postValue(true)
    fun audienceHelp() = _audienceHelp.postValue(true)
    fun onCallFriend(call: Boolean) = _friendHelp.postValue(call)
    fun onAskAudience(audience: Boolean) = _audienceHelp.postValue(audience)


    private fun emitTimerSeconds() {
        timerDisposable.clear()
        _stateTimer.postValue(false)
        gameRepository.gameTimer().subscribe({
            _seconds.postValue(15 - it.toInt())
        }, {
            Log.e("Timer", "Error: ${it.message}")
        }).add(timerDisposable)
    }

    override fun onClickAnswer(answerText: Answer) {
        _answers.value = _answers.value.apply { answerText.state = AnswerState.IS_PRESSED }
        timerDisposable.clear()
        _stateTimer.postValue(true)
        gameRepository.delayTime(2)
            .subscribe { _ ->
                checkAnswerState(answerText)
            }.add(disposable)
    }

    private fun checkAnswerState(answer: Answer) {
        _answers.value = _answers.value.apply {
            if (answer.answer == _question.value?.correctAnswer) {
                answer.state = AnswerState.IS_CORRECT
                gameRepository.delayTime(1).subscribe { _ ->
                        checkQuestionLevel()
                    }.add(disposable)
            } else {
                answer.state = AnswerState.IS_WRONG
                gameRepository.delayTime(1).subscribe { _ ->
                        stageCounter--
                        gameOver()
                    }.add(disposable)
            }
        }
    }


    fun resetGameData() {
        questionCounter = 0
        stageCounter = 1
        difficulty = 0
        getQuestions()
        setStage()
        emitTimerSeconds()
    }
}