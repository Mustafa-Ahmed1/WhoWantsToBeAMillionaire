package com.frenchfriesfamily.whowantstobeamillionaire.utils.bindingAdapters

import android.view.View
import androidx.databinding.BindingAdapter
import com.androchef.happytimer.countdowntimer.CircularCountDownView
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.TimeDurations.FIRST_DURATION
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.TimeDurations.SECOND_DURATION
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.TimeDurations.THIRD_DURATION
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.getColor
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.GameViewModel

@BindingAdapter(value = ["app:progressBarColor"])
fun changeProgressBarDependingOnTime(view: CircularCountDownView, remainingSeconds: Int?) {
    when (remainingSeconds) {
        in THIRD_DURATION -> view.strokeColorForeground = view.getColor(R.color.green)
        in SECOND_DURATION -> view.strokeColorForeground = view.getColor(R.color.yellow)
        in FIRST_DURATION -> view.strokeColorForeground = view.getColor(R.color.red)
    }
}

@BindingAdapter(value = ["app:stopTimeBasedOnState"])
fun stopTimeBasedOnState(view: CircularCountDownView, state: Boolean) {
    if (state) view.pauseTimer()
}
