package com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions

import android.view.View
import androidx.core.content.ContextCompat

fun View.getColor(color: Int): Int {
    return ContextCompat.getColor(this.context, color)
}