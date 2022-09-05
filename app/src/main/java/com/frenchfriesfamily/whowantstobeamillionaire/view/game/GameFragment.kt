package com.frenchfriesfamily.whowantstobeamillionaire.view.game

import android.media.MediaPlayer
import android.util.Log
import androidx.navigation.Navigation
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentGameBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Audio
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.AnswerState

//TODO: clean up the messy mess
class GameFragment :
    BaseFragment<FragmentGameBinding, GameViewModel>(R.layout.fragment_game) {
    override val viewModelClass = GameViewModel::class.java

    override fun setUp() {
        Audio.runAudio(MediaPlayer.create(this.context, R.raw.question))
        if (Audio.muteState == 100) {
            binding.buttonSounds.setImageResource(R.drawable.ic_sounds_on)
        } else {
            binding.buttonSounds.setImageResource(R.drawable.ic_sounds_off)
        }
        viewModel.question.observe(this) {
            Log.i("kkk", it?.question.toString())
        }

        val adapter = GameAdapter(mutableListOf(), viewModel)

        binding.recyclerAnswers.adapter = adapter

        binding.countdownView.apply {
            initTimer(15)
            startTimer()
        }
        sound()

        binding.apply {
            buttonReplaceQuestion.setOnClickListener {
                Audio.runAudio(MediaPlayer.create(context, R.raw.push_audio))
            }
            buttonRemoveTwoAnswers.setOnClickListener {
                Audio.runAudio(MediaPlayer.create(context, R.raw.push_audio))
            }

            viewModel?.question?.observe(this@GameFragment) {
                binding.countdownView.apply {
                    initTimer(15)
                    startTimer()
                }
            }
        }

        viewModel.seconds.observe(this) {
            if (it == Constants.TimeDurations.ZERO) navToResultFragment()
        }

        viewModel.answerState.observe(this) { state ->
            binding.countdownView.apply {
                if (state != AnswerState.IS_DEFAULT) {
                    pauseTimer()
                }
            }
            if (state == AnswerState.IS_WRONG) {
                navToResultFragment()
            }
        }
        viewModel.questionsList.observe(this) {

        }

        navToHomeFragment()
        onClickDialogs()
    }

    private fun navToResultFragment() {
        val currentStage = viewModel.getStageList()[viewModel.stageCounter]
        val action = GameFragmentDirections.actionQuestionFragmentToResultFragment(currentStage)
        Navigation.findNavController(binding.root).navigate(action)

    }

    private fun navToHomeFragment() {
        binding.buttonLeave.setOnClickListener { view ->
            Navigation.findNavController(view)
                .popBackStack()
        }
    }

    private fun onClickDialogs() {
        binding.apply {
            buttonCall.setOnClickListener { view ->
                Audio.runAudio(MediaPlayer.create(activity?.baseContext, R.raw.push_audio))
                viewModel?.onCallFriend(false)
                val action = GameFragmentDirections.actionQuestionFragmentToFriendDialog()
                Navigation.findNavController(view).navigate(action)
            }
            buttonAudience.setOnClickListener { view ->
                viewModel?.onAskAudience(false)
                val action = GameFragmentDirections.actionQuestionFragmentToAudienceDialog()
                Navigation.findNavController(view).navigate(action)
                Audio.runAudio(MediaPlayer.create(activity?.baseContext, R.raw.push_audio))
            }
        }
    }

    private fun sound() {
        binding.buttonSounds.setOnClickListener {
            if (Audio.muteState == 100) {
                binding.buttonSounds.setImageResource(R.drawable.ic_sounds_on)
                Audio.muteAudio(requireContext())
            } else {
                binding.buttonSounds.setImageResource(R.drawable.ic_sounds_off)
                Audio.unmuteAudio(requireContext())
                Audio.runAudio(MediaPlayer.create(activity?.baseContext, R.raw.question))
            }
        }
    }

}