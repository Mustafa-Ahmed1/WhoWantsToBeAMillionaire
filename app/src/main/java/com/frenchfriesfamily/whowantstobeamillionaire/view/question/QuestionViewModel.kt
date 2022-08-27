package com.frenchfriesfamily.whowantstobeamillionaire.view.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.model.network.State
import com.frenchfriesfamily.whowantstobeamillionaire.model.repositories.QuestionsRepository
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.local.LocalResponse
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.QuestionsDto
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.local.LocalResult
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class QuestionViewModel : BaseViewModel() {

    private val repository = QuestionsRepository()
    private val disposable= CompositeDisposable()

    private val _question = MutableLiveData<QuestionsDto>()
    val question: LiveData<QuestionsDto>
        get() = _question

    private val _questionsList = MutableLiveData<State<LocalResponse>?>()
    val questionsList:LiveData<State<LocalResponse>?> =_questionsList

    private val _questionone = MutableLiveData<LocalResult?>()
    val questionone:LiveData<LocalResult?> =_questionone


    private var questionCounter = 0
    private var difficulty = 0


    fun startGame() {
       getQuestions()
    }

    private fun getQuestions() {
        _questionsList.postValue(State.Loading)
        disposable.add(
            repository.getQuestioneList(
                Constants.AMOUNT_OF_QUESTION,
                Constants.DIFFICULTY[difficulty],
                Constants.QUESTION_TYPE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onGetQuestionSuccess, ::onGetQuestionError)
        )
    }

    private fun onGetQuestionSuccess(QuestionResponse: State.Success<LocalResponse>?) {
        _questionsList.value = QuestionResponse
        setQuestion()
    }

    private fun onGetQuestionError(throwable: Throwable) {
        _questionsList.postValue(State.Error(throwable.message.toString()))
    }

    private fun setQuestion(){
        _questionone.postValue(_questionsList.value?.toData()?.questions?.get(questionCounter))
    }

    fun onClickAnyOption(){
        questionCounter++
        if (questionCounter == 5){
                difficulty++
                questionCounter = 0
                getQuestions()
        }
        setQuestion()
    }


    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    init {
        val questionsList = repository.getQuestions()
        _question.postValue(questionsList[questionsList.indices.random()])
    }

    fun testData() {
        val questionsList = repository.getQuestions()
        _question.postValue(questionsList[questionsList.indices.random()])
    }
}