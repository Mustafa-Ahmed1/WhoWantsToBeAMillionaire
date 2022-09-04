package com.frenchfriesfamily.whowantstobeamillionaire.utils.bindingAdapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants
import com.frenchfriesfamily.whowantstobeamillionaire.utils.TimeDuration
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.getColor
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.replacePunctuationTextsWithSymbols

@BindingAdapter(value = ["app:colorStateWithTimer"])
fun changeTextColorDependingOnTime(view: TextView, remainingSeconds: Int?) {
    when (remainingSeconds) {
        in TimeDuration.FIRST_DURATION.duration -> {
            view.setTextColor(view.getColor(R.color.red))
        }
        in TimeDuration.SECOND_DURATION.duration -> {
            view.setTextColor(view.getColor(R.color.yellow))
        }
        else -> {
            view.setTextColor(view.getColor(R.color.green))
        }
    }
}

@BindingAdapter(value = ["app:formatTextBySymbols"])
fun replacePunctuationTextsWithSymbols(view: TextView, text: String?) {
    view.text = text?.replacePunctuationTextsWithSymbols()
}