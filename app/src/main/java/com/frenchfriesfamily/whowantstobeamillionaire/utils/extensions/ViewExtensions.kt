package com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions

import android.view.View
import androidx.core.content.ContextCompat

fun View.getColor(color: Int) = ContextCompat.getColor(this.context, color)

fun View.setBackground(drawableId: Int) {
    this.background = ContextCompat.getDrawable(this.context, drawableId)
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}