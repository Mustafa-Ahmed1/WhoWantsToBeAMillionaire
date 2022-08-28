package com.frenchfriesfamily.whowantstobeamillionaire.view.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.model.repositories.QuestionsRepository
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.QuestionsDto
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel

class AboutViewModel : BaseViewModel() {

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