package com.frenchfriesfamily.whowantstobeamillionaire.utils.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["app:isUsed"])
fun isUsed(view: ImageView, Clicked: Boolean) {
    if (!Clicked) {
        view.apply {
            isClickable = Clicked
            setAlpha(80)
        }
    }
}