package com.frenchfriesfamily.whowantstobeamillionaire.utils.bindingAdapters

import androidx.databinding.BindingAdapter
import com.androchef.happytimer.countdowntimer.CircularCountDownView
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.utils.TimeDuration
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.getColor

@BindingAdapter(value = ["app:colorStateWithTimer"])
fun changeCircularTimerColorDependingOnTime(view: CircularCountDownView, remainingSeconds: Int?) {
    when (remainingSeconds) {
        in TimeDuration.THIRD_DURATION.duration -> {
            view.strokeColorForeground = view.getColor(R.color.green)
        }
        in TimeDuration.SECOND_DURATION.duration -> {
            view.strokeColorForeground = view.getColor(R.color.yellow)
        }
        in TimeDuration.FIRST_DURATION.duration -> {
            view.strokeColorForeground = view.getColor(R.color.red)
        }
    }
}