package com.frenchfriesfamily.whowantstobeamillionaire.view.base

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.frenchfriesfamily.whowantstobeamillionaire.BR

abstract class BaseAdapter<T>(
    private var _items: List<T>,
    private val _listener: BaseInteractionListener
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    val items get() = _items
    abstract val layoutID: Int

    fun setItems(newItems: List<T>) {
        val changResult = DiffUtil.calculateDiff(AdapterDiffUtil(_items, newItems))
        _items = newItems
        changResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), layoutID, parent, false
            )
        )

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> bind(holder, position)
        }
    }

    override fun getItemCount() = _items.size

    private fun bind(holder: ItemViewHolder, position: Int) {
        holder.binding.apply {
            setVariable(BR.item, _items[position])
            setVariable(BR.listener, _listener)
        }
    }

    class ItemViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)

    abstract class BaseViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}