package com.frenchfriesfamily.whowantstobeamillionaire.view.question

import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseAdapter
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseInteractionListener

class QuestionAdapter(
    var list: List<List<String?>?>,
    listener: QuestionInteractionListener
) : BaseAdapter<List<String?>?>(list, listener) {

    override val layoutID = R.layout.item_answer

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface QuestionInteractionListener : BaseInteractionListener {

    }

}


