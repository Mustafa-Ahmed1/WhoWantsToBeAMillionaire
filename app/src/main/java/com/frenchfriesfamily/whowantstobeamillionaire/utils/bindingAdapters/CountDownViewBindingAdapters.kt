package com.frenchfriesfamily.whowantstobeamillionaire.utils.bindingAdapters

import androidx.databinding.BindingAdapter
import com.androchef.happytimer.countdowntimer.CircularCountDownView
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.TimeDurations.FIRST_DURATION
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.TimeDurations.SECOND_DURATION
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.TimeDurations.THIRD_DURATION
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.getColor
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.TimeDuration

@BindingAdapter(value = ["app:colorStateWithTimer"])
fun changeCircularTimerColorDependingOnTime(view: CircularCountDownView, remainingSeconds: Int?) {
    when (remainingSeconds) {
        in THIRD_DURATION -> view.strokeColorForeground = view.getColor(R.color.green)
        in SECOND_DURATION -> view.strokeColorForeground = view.getColor(R.color.yellow)
        in FIRST_DURATION -> view.strokeColorForeground = view.getColor(R.color.red)
    }
}