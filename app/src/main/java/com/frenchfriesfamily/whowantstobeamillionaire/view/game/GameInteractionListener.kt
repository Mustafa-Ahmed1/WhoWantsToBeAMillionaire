package com.frenchfriesfamily.whowantstobeamillionaire.view.game

import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseInteractionListener
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.Answer

interface GameInteractionListener : BaseInteractionListener {
    fun onClickAnswer(answerText: Answer)
}