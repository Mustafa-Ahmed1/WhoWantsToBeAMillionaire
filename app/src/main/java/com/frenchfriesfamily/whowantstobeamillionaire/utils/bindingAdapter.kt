package com.frenchfriesfamily.whowantstobeamillionaire.view.stage

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.frenchfriesfamily.whowantstobeamillionaire.model.data.StageDetails
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseAdapter

@BindingAdapter(value = ["app:items"])
fun <T:Any> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}

@BindingAdapter(value = ["app:itemsShuffled"])
fun <T:Any> setRecyclerItemsShuffled(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items?.shuffled() ?: emptyList())
}
