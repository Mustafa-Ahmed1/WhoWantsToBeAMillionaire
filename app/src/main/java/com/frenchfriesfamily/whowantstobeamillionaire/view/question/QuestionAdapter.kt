package com.frenchfriesfamily.whowantstobeamillionaire.view.question

import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseAdapter
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseInteractionListener

class QuestionAdapter(
    var list: List<List<String?>?>,
    listener: QuestionInteractionListener
) : BaseAdapter<List<String?>?>(list, listener) {

    override val layoutID = R.layout.item_answer

}


