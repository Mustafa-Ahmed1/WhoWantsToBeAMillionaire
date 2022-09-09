package com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions

import com.frenchfriesfamily.whowantstobeamillionaire.model.response.Question
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.Answer
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.AnswerState

fun Question.toAnswer(): List<Answer>? {
    val list = this.incorrectAnswers?.toMutableList()?.apply {
        this.add(correctAnswer)
    }?.map { Answer(it, AnswerState.IS_DEFAULT) }?.shuffled()
    return list
}
