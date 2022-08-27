package com.frenchfriesfamily.whowantstobeamillionaire.view.base

import androidx.recyclerview.widget.DiffUtil
import com.frenchfriesfamily.whowantstobeamillionaire.model.data.StageDetails

class StageDiffUtil(
    private val mOldStage: List<StageDetails>,
    private val mNewStage: List<StageDetails>
) :
    DiffUtil.Callback() {
    override fun getOldListSize() = mOldStage.size

    override fun getNewListSize() = mNewStage.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (mOldStage[oldItemPosition].reward == mNewStage[oldItemPosition].reward
                && mOldStage[oldItemPosition].level == mNewStage[oldItemPosition].level)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}