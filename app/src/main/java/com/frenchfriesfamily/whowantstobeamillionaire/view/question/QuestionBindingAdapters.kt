package com.frenchfriesfamily.whowantstobeamillionaire.view.question

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.androchef.happytimer.countdowntimer.CircularCountDownView
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.TimeDurations.FIRST_THIRD_DURATION
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.TimeDurations.MAX_DURATION
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.TimeDurations.SECOND_THIRD_DURATION
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.TimeDurations.ZERO
import com.frenchfriesfamily.whowantstobeamillionaire.utils.ViewColor

@BindingAdapter(value = ["app:colorStateWithTimer"])
fun changeTextColorDependingOnTime(view: TextView, remainingSeconds: Int?) {
    when (remainingSeconds) {
        in ZERO..FIRST_THIRD_DURATION -> {
            view.setTextColor(ViewColor(view).red)
        }
        in (FIRST_THIRD_DURATION + 1)..SECOND_THIRD_DURATION -> {
            view.setTextColor(ViewColor(view).yellow)
        }
        else -> {
            view.setTextColor(ViewColor(view).green)
        }
    }
}

@BindingAdapter(value = ["app:colorStateWithTimer"])
fun changeCircularTimerColorDependingOnTime(view: CircularCountDownView, remainingSeconds: Int?) {
    when (remainingSeconds) {
        in ZERO..FIRST_THIRD_DURATION -> {
            view.strokeColorForeground = ViewColor(view).red
        }
        in (FIRST_THIRD_DURATION + 1)..SECOND_THIRD_DURATION -> {
            view.strokeColorForeground = ViewColor(view).yellow
        }
        else -> {
            view.strokeColorForeground = ViewColor(view).green
        }
    }
}