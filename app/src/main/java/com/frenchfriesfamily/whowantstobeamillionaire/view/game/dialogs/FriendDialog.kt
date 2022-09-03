package com.frenchfriesfamily.whowantstobeamillionaire.view.game.dialogs

import android.media.MediaPlayer
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.DialogFriendBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Audio
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseDialogFragment
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.GameViewModel
import kotlin.random.Random

class FriendDialog :
    BaseDialogFragment<DialogFriendBinding, GameViewModel>(R.layout.dialog_friend) {

    override val viewModelClass = GameViewModel::class.java

    override fun onStart() {
        super.onStart()
        binding.buttonOk.setOnClickListener {
            Audio.runAudio(MediaPlayer.create(this.context, R.raw.push_audio))
            dismiss()
        }
        viewModel.question.observe(this) {
            val correctAnswerSymbol = calculateProbabilityOfAnswers(it?.correctAnswer)
            binding.textAnswer.text = correctAnswerSymbol.toString()
        }
    }

    private fun calculateProbabilityOfAnswers(correctAnswer: String?): Char {
        var friendAnswer = 'A'
        viewModel.answers.observe(this) {
            val probabilites = mutableListOf<Float>()

            it?.forEach { answer ->
                if (answer == correctAnswer) {
                    probabilites.add(Random.nextFloat() + 1f)
                } else {
                    probabilites.add(Random.nextFloat())
                }
            }
            val maximumValue = probabilites.max()
            friendAnswer = (probabilites.indexOf(maximumValue) + 65).toChar()
        }
        return friendAnswer
    }

}