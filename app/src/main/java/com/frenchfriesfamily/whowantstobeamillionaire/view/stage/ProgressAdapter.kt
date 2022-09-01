package com.frenchfriesfamily.whowantstobeamillionaire.view.stage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.ItemStageBinding
import com.frenchfriesfamily.whowantstobeamillionaire.model.data.StageDetails
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseAdapter
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseInteractionListener


class ProgressAdapter(
    list: List<StageDetails>,
    listener: StageInteractionListener
) : BaseAdapter<StageDetails>(list, listener) {

    override val layoutID =R.layout.item_stage

    interface StageInteractionListener : BaseInteractionListener {

    }
}
