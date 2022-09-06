package com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions

import android.view.View
import androidx.core.content.ContextCompat

//TODO: add more view extensions to simplify the code
fun View.getColor(color: Int): Int {
    return ContextCompat.getColor(this.context, color)
}

fun View.setBackground(drawableId: Int) {
    this.background = ContextCompat.getDrawable(this.context, drawableId)
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}