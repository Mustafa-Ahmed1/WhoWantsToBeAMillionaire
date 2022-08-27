package com.frenchfriesfamily.whowantstobeamillionaire.view.stage

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.frenchfriesfamily.whowantstobeamillionaire.model.data.StageDetails


@BindingAdapter(value = ["app:items"])
fun setRecyclerItems(view: RecyclerView?, items: List<StageDetails>?) {
    (view?.adapter as ProgressAdapter?)?.setItems(items ?: emptyList())
}
