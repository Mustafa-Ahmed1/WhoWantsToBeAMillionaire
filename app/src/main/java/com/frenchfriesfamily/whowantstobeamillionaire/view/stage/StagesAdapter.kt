package com.frenchfriesfamily.whowantstobeamillionaire.view.stage

import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.model.data.StageDetails
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseAdapter
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseInteractionListener


class StagesAdapter(list: List<StageDetails>, listener: StageInteractionListener) :
    BaseAdapter<StageDetails>(list, listener) {

    override val layoutID = R.layout.item_stage

    interface StageInteractionListener : BaseInteractionListener

}
