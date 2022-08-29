package com.frenchfriesfamily.whowantstobeamillionaire.view.question

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.model.network.State
import com.frenchfriesfamily.whowantstobeamillionaire.model.repositories.QuestionsRepository
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.QuestionResult
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.add
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class QuestionViewModel : BaseViewModel() {

    private val repository = QuestionsRepository()
    private val disposable = CompositeDisposable()


    private val _questionsList = MutableLiveData<State<List<QuestionResult>?>>()
    val questionsList: LiveData<State<List<QuestionResult>?>> = _questionsList


    private val _question = MutableLiveData<QuestionResult?>()
    val question: LiveData<QuestionResult?> = _question


    private var questionCounter = 0
    private var difficulty = 0


    init {
        getQuestions()
    }

    private fun getQuestions() {
        repository.getQuestioneList(
            Constants.AMOUNT_OF_QUESTION,
            Constants.DIFFICULTY[difficulty],
            Constants.QUESTION_TYPE
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.i("VIEWMODEL", "got ${it}")
                if (it is State.Success) {
                    _questionsList.postValue(State.Success(it.toData()?.results))
                }
            }.add(disposable)
        setQuestion()
    }


    private fun setQuestion() {
        _question.postValue(_questionsList.value?.toData()?.get(questionCounter))
    }


    fun onClickAnyOption() {
        questionCounter++
        if (questionCounter == 5) {
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

}