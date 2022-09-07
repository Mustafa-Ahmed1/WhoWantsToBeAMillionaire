package com.frenchfriesfamily.whowantstobeamillionaire.utils.bindingAdapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseAdapter
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.GameAdapter
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.GameViewModel

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}

@BindingAdapter(value = ["app:gameAdapter"])
fun setGameAdapter(view: RecyclerView, viewModel: GameViewModel) {
    view.adapter = GameAdapter(mutableListOf(), viewModel)
}