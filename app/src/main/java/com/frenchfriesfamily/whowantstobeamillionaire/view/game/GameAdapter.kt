package com.frenchfriesfamily.whowantstobeamillionaire.view.game

import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseAdapter
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.Answer

class GameAdapter(var list: List<Answer>, listener: GameInteractionListener) :
    BaseAdapter<Answer>(list, listener) {
    override val layoutID = R.layout.item_answer
}