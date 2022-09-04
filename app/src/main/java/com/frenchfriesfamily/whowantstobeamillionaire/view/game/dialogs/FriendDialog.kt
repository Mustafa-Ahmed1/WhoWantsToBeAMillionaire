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
    private val audioInFriendDialog =Audio()
    override val viewModelClass = GameViewModel::class.java

    override fun onStart() {
        super.onStart()
        binding.buttonOk.setOnClickListener {
            audioInFriendDialog.runAudio(MediaPlayer.create(this.context, R.raw.push_audio))
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
            val probabilities = mutableListOf<Float>()

            it?.forEach { answer ->
                if (answer == correctAnswer) {
                    probabilities.add(Random.nextFloat() + 1f)
                } else {
                    probabilities.add(Random.nextFloat())
                }
            }
            val maximumValue = probabilities.max()
            friendAnswer = (probabilities.indexOf(maximumValue) + 65).toChar()
        }
        return friendAnswer
    }

}