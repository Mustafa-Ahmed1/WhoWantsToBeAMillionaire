package com.frenchfriesfamily.whowantstobeamillionaire.view.question

import android.media.MediaPlayer
import android.util.Log
import androidx.navigation.Navigation
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentQuestionBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Audio
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment

class QuestionFragment :
    BaseFragment<FragmentQuestionBinding, QuestionViewModel>(R.layout.fragment_question) {

    override val viewModelClass = QuestionViewModel::class.java

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

        val adapter = QuestionAdapter(mutableListOf(), viewModel)
        binding.recyclerAnswers.adapter = adapter

        binding.countdownView.apply {
            initTimer(15)
            startTimer()
        }
        sound()

        binding.buttonReplaceQuestion.setOnClickListener {
            Audio.runAudio(MediaPlayer.create(this.context, R.raw.push_audio))
        }

        binding.buttonRemoveTwoAnswers.setOnClickListener {
            Audio.runAudio(MediaPlayer.create(this.context, R.raw.push_audio))
        }

        navToHomeFragment()
        navToResultFragment()
        onClickDialogs()
    }

    private fun navToResultFragment() {
        binding.buttonRemoveTwoAnswers.setOnClickListener { view ->
            val currentStage = viewModel.getStageList()[viewModel.stageCounter - 1]
            val action =
                QuestionFragmentDirections.actionQuestionFragmentToResultFragment(currentStage)
            Navigation.findNavController(view).navigate(action)
        }
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
                val action = QuestionFragmentDirections.actionQuestionFragmentToFriendDialog()
                Navigation.findNavController(view).navigate(action)
            }
            buttonAudience.setOnClickListener { view ->
                viewModel?.onAskAudience(false)
                val action = QuestionFragmentDirections.actionQuestionFragmentToAudienceDialog()
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