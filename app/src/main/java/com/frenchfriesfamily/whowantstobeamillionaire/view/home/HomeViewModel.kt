package com.frenchfriesfamily.whowantstobeamillionaire.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.model.repositories.QuestionsRepository
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.QuestionsDto
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

//TODO : implement HomeViewModel code
class HomeViewModel : BaseViewModel() {
    private val repository = QuestionsRepository()

    private val _question = MutableLiveData<QuestionsDto>()
    val question: LiveData<QuestionsDto>
        get() = _question


    init {
        val questionsList = repository.getQuestions()
        _question.postValue(questionsList[questionsList.indices.random()])
    }

    fun testData() {
        val questionsList = repository.getQuestions()
        _question.postValue(questionsList[questionsList.indices.random()])
    }

}