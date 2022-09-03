package com.frenchfriesfamily.whowantstobeamillionaire.view.game

import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseAdapter

class GameAdapter(var list: List<List<String?>?>, listener: GameInteractionListener) :
    BaseAdapter<List<String?>?>(list, listener) {

    override val layoutID = R.layout.item_answer

}