package com.frenchfriesfamily.whowantstobeamillionaire.view.stage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.ItemStageBinding
import com.frenchfriesfamily.whowantstobeamillionaire.model.data.StageDetails

class ProgressAdapter(
    private var list: List<StageDetails>,
    private val listener: StageInteractionListener
) : RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_stage, parent, false)
        return ProgressViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProgressViewHolder, position: Int) {
        val currentProgress = list[position]
        holder.binding.item = currentProgress
    }

    override fun getItemCount() = list.size

    class ProgressViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemStageBinding.bind(viewItem)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems: List<StageDetails>) {
        list = newItems
        notifyDataSetChanged()
    }
}