package com.frenchfriesfamily.whowantstobeamillionaire.view.question

import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseInteractionListener

interface QuestionInteractionListener : BaseInteractionListener {
    fun onClickAnswer(answerText: String)
}