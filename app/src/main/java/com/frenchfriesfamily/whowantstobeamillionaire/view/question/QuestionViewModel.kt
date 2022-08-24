package com.frenchfriesfamily.whowantstobeamillionaire.view.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.model.repositories.QuestionsRepository
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.QuestionsDto
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel

class QuestionViewModel : BaseViewModel() {
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