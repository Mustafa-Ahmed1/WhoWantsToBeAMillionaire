package com.frenchfriesfamily.whowantstobeamillionaire.utils.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.setBackground

@BindingAdapter(value = ["app:isUsed"])
fun isUsed(view: ImageView, isClicked: Boolean) {
    view.alpha = if (isClicked) 1f else 0.3f
    view.isClickable = isClicked
}

@BindingAdapter(value = ["app:soundsState"])
fun setImageBasedOnSoundsState(view: ImageView, isMuted: Boolean?) {
    when (isMuted) {
        true -> view.setBackground(R.drawable.ic_sounds_off)
        else -> view.setBackground(R.drawable.ic_sounds_on)
    }
}