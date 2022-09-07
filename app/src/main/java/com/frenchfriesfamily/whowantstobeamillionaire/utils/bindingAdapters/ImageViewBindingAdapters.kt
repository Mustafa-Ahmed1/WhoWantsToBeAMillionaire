package com.frenchfriesfamily.whowantstobeamillionaire.utils.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.setBackground

@BindingAdapter(value = ["app:isUsed"])
fun isUsed(view: ImageView, Clicked: Boolean) {
    if (!Clicked) {
        view.apply {
            isClickable = Clicked
            setAlpha(80)
        }
    }
}

@BindingAdapter(value = ["app:soundsState"])
fun setImageBasedOnSoundsState(view: ImageView, isMuted: Boolean?) {
    when (isMuted) {
        true -> view.setBackground(R.drawable.ic_sounds_off)
        else -> view.setBackground(R.drawable.ic_sounds_on)
    }
}