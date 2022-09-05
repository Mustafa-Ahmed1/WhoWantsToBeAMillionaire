package com.frenchfriesfamily.whowantstobeamillionaire.utils.bindingAdapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.getColor
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.getProbableAnswer
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.replacePunctuationTextsWithSymbols
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.TimeDuration

@BindingAdapter(value = ["app:colorStateWithTimer"])
fun changeTextColorDependingOnTime(view: TextView, remainingSeconds: Int?) {
    when (remainingSeconds) {
        in TimeDuration.THIRD_DURATION.duration -> view.setTextColor(view.getColor(R.color.green))
        in TimeDuration.SECOND_DURATION.duration -> view.setTextColor(view.getColor(R.color.yellow))
        in TimeDuration.FIRST_DURATION.duration -> view.setTextColor(view.getColor(R.color.red))
    }
}

@BindingAdapter(value = ["app:textFormattingBySymbols"])
fun formatTextBySymbols(view: TextView, text: String?) {
    view.text = text?.replacePunctuationTextsWithSymbols()
}

@BindingAdapter(value = ["app:soundsState"])
fun setTextBasedOnSoundsState(view: TextView, isMuted: Boolean?) {
    if (isMuted == true) {
        view.text = view.context.getString(R.string.sounds_off)
    } else {
        view.text = view.context.getString(R.string.sounds_on)
    }
}

@BindingAdapter("app:correctAnswer", "app:allAnswers")
fun setTextToProbableAnswer(view: TextView, correctAnswer: String?, allAnswers: List<String?>?) {
    view.text = correctAnswer?.getProbableAnswer(allAnswers).toString()
}