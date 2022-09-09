package com.frenchfriesfamily.whowantstobeamillionaire.view.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.model.data.StageDetails
import com.frenchfriesfamily.whowantstobeamillionaire.model.network.State
import com.frenchfriesfamily.whowantstobeamillionaire.model.repositories.StagesRepositoryImpl
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.GameResponse
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.Question
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.DIFFICULTY.LAST_EASY_QUESTION
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.DIFFICULTY.LAST_MEDIUM_QUESTION
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.DIFFICULTY.LAST_HARD_QUESTION
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Game.AMOUNT_OF_QUESTION
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Game.DIFFICULTY
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Game.QUESTION_TYPE
import com.frenchfriesfamily.whowantstobeamillionaire.utils.TimerUtils
import com.frenchfriesfamily.whowantstobeamillionaire.utils.event.Event
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.add
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.postEvent
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.toAnswer
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.Answer
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.AnswerState
import io.reactivex.rxjava3.disposables.CompositeDisposable

class GameViewModel : BaseViewModel(), GameInteractionListener {

    private val stagesRepository = StagesRepositoryImpl()
    private val timerUtils = TimerUtils()
    private val timerDisposable = CompositeDisposable()

    private val _questions = MutableLiveData<State<List<Question>?>>()
    val questions: LiveData<State<List<Question>?>> = _questions

    private val _question = MutableLiveData<Question?>()
    val question: LiveData<Question?> = _question

    private val _answers = MutableLiveData<List<Answer>?>()
    val answers: LiveData<List<Answer>?> = _answers


    private val _remainingSeconds = MutableLiveData(15)
    val remainingSeconds: LiveData<Int> = _remainingSeconds

    private val _isEmittingSeconds = MutableLiveData<Boolean>()
    val isEmittingSeconds: LiveData<Boolean> = _isEmittingSeconds


    private val _isChangeQuestionHelpAvailable = MutableLiveData(true)
    val isChangeQuestionHelpAvailable: LiveData<Boolean> = _isChangeQuestionHelpAvailable

    private val _isCallFriendHelpAvailable = MutableLiveData(true)
    val isCallFriendHelpAvailable: LiveData<Boolean> = _isCallFriendHelpAvailable

    private val _isAudienceHelpAvailable = MutableLiveData(true)
    val isAudienceHelpAvailable: LiveData<Boolean> = _isAudienceHelpAvailable


    private val _stage = MutableLiveData<StageDetails>()
    val stage: LiveData<StageDetails> = _stage


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


    val onAudienceClicked = { _audienceClick.postEvent() }
    val onCallFriendClicked = { _callFriendClick.postEvent() }
    val onOkClicked = { _okCLick.postEvent() }
    val onExitCLicked = { _exitClick.postEvent() }
    val onStayClicked = { _stayCLick.postEvent() }
    val onLeaveClicked = { _leaveClick.postEvent() }


    val onChangeQuestion = {
        _isChangeQuestionHelpAvailable.postValue(false)
        currentQuestion++
        setQuestion()
    }

    private var currentQuestion = 0
    var currentStage = 1
    private var difficulty = 0


    init {
        getQuestions()
        setStage()
    }


    private fun getQuestions() {
        gameRepository.getQuestions(
            AMOUNT_OF_QUESTION,
            DIFFICULTY[difficulty],
            QUESTION_TYPE
        )
            .subscribe(::onGetQuestionSuccess, ::onGetQuestionError)
            .add(disposable)
    }

    private fun onGetQuestionSuccess(response: State<GameResponse>) {
        when (response) {
            is State.Loading -> _questions.postValue(response)
            is State.Success -> onStateSuccess(response)
            is State.Error -> onStateError(response.message)
        }
    }

    private fun onStateError(message: String) {
        timerDisposable.clear()
        _questions.postValue(State.Error(message))
    }

    private fun onStateSuccess(response: State<GameResponse>) {
        _questions.value = State.Success(response.toData()?.results)
        setQuestion()
    }

    private fun onGetQuestionError(throwable: Throwable) {
        _questions.postValue(State.Error(throwable.message.toString()))
    }

    private fun setQuestion() {
        startTimer()
        _questions.value?.toData()?.get(currentQuestion).apply {
            _question.postValue(this)
            _answers.postValue(this?.toAnswer())
        }
    }


    private fun startTimer() {
        stopTimer()
        timerUtils.gameTimer().subscribe {
            _remainingSeconds.postValue(15 - it.toInt())
        }.add(timerDisposable)
    }

    private fun stopTimer() {
        timerDisposable.clear()
        _isEmittingSeconds.postValue(false)
    }


    fun onCallFriend(call: Boolean) {
        _isCallFriendHelpAvailable.postValue(call)
    }

    fun onAskAudience(audience: Boolean) {
        _isAudienceHelpAvailable.postValue(audience)
    }

    fun checkQuestionLevel() {
        startTimer()
        when (currentStage) {
            LAST_EASY_QUESTION -> nextDifficulty()
            LAST_MEDIUM_QUESTION -> nextDifficulty()
            LAST_HARD_QUESTION -> gameOver()
            else -> nextQuestion()
        }
    }

    private fun nextDifficulty() {
        currentQuestion = 0
        currentStage++
        difficulty++
        setStage()
        getQuestions()
    }


    fun gameOver() {
        _gameOver.postEvent()
        currentStage--
    }

    private fun nextQuestion() {
        currentQuestion++
        currentStage++
        setStage()
        setQuestion()
    }

    private fun setStage() {
        val stageList = getStageList()
        _stage.postValue(stageList.get(currentStage))
    }

    fun getStageList() = stagesRepository.getStages().reversed()


    override fun onClickAnswer(answer: Answer) {
        _answers.postValue(_answers.value.apply { answer.state = AnswerState.IS_PRESSED })
        stopTimer()
        timerUtils.delayTime(2)
            .subscribe { _ ->
                checkAnswerState(answer)
            }.add(disposable)
        _isEmittingSeconds.postValue(true)
    }

    private fun checkAnswerState(answer: Answer) {
        _answers.postValue(
            _answers.value.apply {
                val selectedAnswer = answer.answer
                val correctAnswer = _question.value?.correctAnswer

                if (selectedAnswer.equals(correctAnswer)) {
                    answer.state = AnswerState.IS_CORRECT

                    timerUtils.delayTime(1).subscribe { _ ->
                        checkQuestionLevel()
                    }.add(disposable)
                } else {
                    answer.state = AnswerState.IS_WRONG

                    timerUtils.delayTime(1).subscribe { _ ->
                        gameOver()
                    }.add(disposable)
                }
            }
        )
    }


    fun resetGameData() {
        getQuestions()
        stopTimer()
        setInitialValues()
        setStage()
    }

    private fun setInitialValues() {
        _remainingSeconds.postValue(15)
        _isEmittingSeconds.postValue(true)
        _isChangeQuestionHelpAvailable.postValue(true)
        _isAudienceHelpAvailable.postValue(true)
        _isCallFriendHelpAvailable.postValue(true)
        currentQuestion = 0
        currentStage = 1
        difficulty = 0
    }


}