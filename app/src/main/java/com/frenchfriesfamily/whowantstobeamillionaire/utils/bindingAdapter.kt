package com.frenchfriesfamily.whowantstobeamillionaire.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseAdapter

@BindingAdapter(value = ["app:items"])
fun <T:Any> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}
