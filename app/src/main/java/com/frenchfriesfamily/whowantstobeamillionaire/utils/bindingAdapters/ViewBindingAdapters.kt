package com.frenchfriesfamily.whowantstobeamillionaire.utils.bindingAdapters

import android.view.View
import androidx.databinding.BindingAdapter
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.model.network.State
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Audio
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.hide
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.setBackground
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.show
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

@BindingAdapter(value = ["app:isMute", "app:audio"])
fun setSoundState(view: View, isMute: Boolean?, audio: Audio?) {
    when (isMute) {
        true -> audio?.muteAudio(view.context)
        else -> audio?.unMuteAudio(view.context)
    }
}

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading(view: View, state: State<T>?) {
    when (state) {
        is State.Loading -> view.show()
        else -> view.hide()
    }
}

@BindingAdapter(value = ["app:showWhenError"])
fun <T> showWhenError(view: View, state: State<T>?) {
    when (state) {
        is State.Error -> view.show()
        else -> view.hide()
    }
}

@BindingAdapter(value = ["app:showWhenSuccess"])
fun <T> showWhenSuccess(view: View, state: State<T>?) {
    when (state) {
        is State.Success -> view.show()
        else -> view.hide()
    }
}