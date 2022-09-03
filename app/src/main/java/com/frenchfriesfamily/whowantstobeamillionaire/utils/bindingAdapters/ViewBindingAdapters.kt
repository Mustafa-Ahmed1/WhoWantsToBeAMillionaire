package com.frenchfriesfamily.whowantstobeamillionaire.utils.bindingAdapters

import android.view.View
import androidx.databinding.BindingAdapter
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.setBackground
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.AnswerState

@BindingAdapter(value = ["app:answerState"])
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