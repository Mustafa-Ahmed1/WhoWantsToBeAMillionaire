package com.frenchfriesfamily.whowantstobeamillionaire.view.game

import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseInteractionListener

interface GameInteractionListener : BaseInteractionListener {
    fun onClickAnswer(answerText: String)
}