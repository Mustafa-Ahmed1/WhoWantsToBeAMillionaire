package com.frenchfriesfamily.whowantstobeamillionaire.view.base

import androidx.recyclerview.widget.DiffUtil

class AdapterDiffUtil<T>(private val oldList: List<T>, private val newList: List<T>) :
    DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        (oldList[oldItemPosition] == newList[newItemPosition])


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true
}