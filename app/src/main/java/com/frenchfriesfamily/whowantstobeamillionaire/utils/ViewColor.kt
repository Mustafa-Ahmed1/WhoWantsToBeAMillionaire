package com.frenchfriesfamily.whowantstobeamillionaire.utils

import android.view.View
import androidx.core.content.ContextCompat
import com.frenchfriesfamily.whowantstobeamillionaire.R

class ViewColor(view: View) {

    val yellow = ContextCompat.getColor(view.context, R.color.yellow)
    val red = ContextCompat.getColor(view.context, R.color.red)
    val green = ContextCompat.getColor(view.context, R.color.green)

}