package com.frenchfriesfamily.whowantstobeamillionaire.view.question

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.androchef.happytimer.countdowntimer.CircularCountDownView
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.model.AnswerState
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.TimeDurations.FIRST_THIRD_DURATION
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.TimeDurations.SECOND_THIRD_DURATION
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.TimeDurations.ZERO
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.getColor
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.replacePunctuationTextsWithSymbols
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.setBackground

//TODO: improve question binding adapters
@BindingAdapter(value = ["app:colorStateWithTimer"])
fun changeTextColorDependingOnTime(view: TextView, remainingSeconds: Int?) {
    when (remainingSeconds) {
        in ZERO..FIRST_THIRD_DURATION -> {
            view.setTextColor(view.getColor(R.color.red))
        }
        in (FIRST_THIRD_DURATION + 1)..SECOND_THIRD_DURATION -> {
            view.setTextColor(view.getColor(R.color.yellow))
        }
        else -> {
            view.setTextColor(view.getColor(R.color.green))
        }
    }
}

@BindingAdapter(value = ["app:colorStateWithTimer"])
fun changeCircularTimerColorDependingOnTime(view: CircularCountDownView, remainingSeconds: Int?) {
    when (remainingSeconds) {
        in ZERO..FIRST_THIRD_DURATION -> {
            view.strokeColorForeground = view.getColor(R.color.red)
        }
        in (FIRST_THIRD_DURATION + 1)..SECOND_THIRD_DURATION -> {
            view.strokeColorForeground = view.getColor(R.color.yellow)
        }
        else -> {
            view.strokeColorForeground = view.getColor(R.color.green)
        }
    }
}

@BindingAdapter(value = ["app:formatTextBySymbols"])
fun replacePunctuationTextsWithSymbols(view: TextView, text: String?) {
    view.text = text?.replacePunctuationTextsWithSymbols()
}

@BindingAdapter(value = ["app:isHelped"])
fun isHelped(view: ImageView, Clicked: Boolean) {
    if (!Clicked) {
        view.apply {
            isClickable = Clicked
            alpha = 0.8f
        }
    }
}

@BindingAdapter(value = ["answerState"])
fun changeAnswerStyle(view: View, state: AnswerState?) {
    when (state) {
        AnswerState.IS_PRESSED -> view.setBackground(R.drawable.rectangle_answer_pressed)
        AnswerState.IS_CORRECT -> view.setBackground(R.drawable.rectangle_answer_correct)
        AnswerState.IS_WRONG -> view.setBackground(R.drawable.rectangle_answer_wrong)
        else -> view.setBackground(R.drawable.rectangle_answer_default)
    }
}

@BindingAdapter(value = ["app:disableViewBasedOnState"])
fun disableViewBasedOnState(view: View, state: AnswerState?) {
    view.isClickable = state == AnswerState.IS_DEFAULT
}