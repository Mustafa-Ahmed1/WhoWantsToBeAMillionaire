package com.frenchfriesfamily.whowantstobeamillionaire.view.game

import android.content.Context
import android.media.MediaPlayer
import androidx.navigation.Navigation
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentGameBinding
import com.frenchfriesfamily.whowantstobeamillionaire.view.AudioViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.AnswerState

class GameFragment :
    BaseFragment<FragmentGameBinding, GameViewModel, AudioViewModel>(R.layout.fragment_game) {

    override val viewModelClass = GameViewModel::class.java
    override val audioViewModelClass = AudioViewModel::class.java

    private lateinit var gameMusic: MediaPlayer

    override fun onAttach(context: Context) {
        super.onAttach(context)
        gameMusic = MediaPlayer.create(context, R.raw.game_audio)
        gameMusic.start()
    }

    override fun setUp() {
        val adapter = GameAdapter(mutableListOf(), viewModel)
        binding.recyclerAnswers.adapter = adapter

        handleTimer()
        onClickButtons()
    }


    // TODO: should be improved
    private fun handleTimer() {
        binding.countdownView.apply {
            initTimer(15)
            startTimer()
        }

        viewModel.question.observe(this@GameFragment) {
            binding.countdownView.apply {
                initTimer(15)
                startTimer()
            }
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
    }


    private fun onClickButtons() {
        navToHomeFragment()
        onClickAudienceButton()
        onClickCallButton()
        onClickReplaceButton()
        onClickRemoveButton()
    }

    private fun navToHomeFragment() {
        binding.buttonLeave.setOnClickListener { view ->
            audioViewModel.audio.playButtonSound(requireContext())
            Navigation.findNavController(view)
                .popBackStack()
        }
    }

    private fun navToResultFragment() {
        val currentStage = viewModel.getStageList()[viewModel.questionCounter]
        val action =
            GameFragmentDirections.actionQuestionFragmentToResultFragment(currentStage)
        Navigation.findNavController(binding.root).navigate(action)
    }

    private fun onClickAudienceButton() {
        binding.buttonAudience.setOnClickListener { view ->
            audioViewModel.audio.playButtonSound(requireContext())
            viewModel.onAskAudience(false)
            val action = GameFragmentDirections.actionQuestionFragmentToAudienceDialog()
            Navigation.findNavController(view).navigate(action)
        }
    }

    private fun onClickCallButton() {
        binding.buttonCall.setOnClickListener { view ->
            audioViewModel.audio.playButtonSound(requireContext())
            viewModel.onCallFriend(false)
            val action = GameFragmentDirections.actionQuestionFragmentToFriendDialog()
            Navigation.findNavController(view).navigate(action)
        }
    }

    private fun onClickReplaceButton() {
        binding.buttonReplaceQuestion.setOnClickListener {
            audioViewModel.audio.playButtonSound(requireContext())
        }
    }

    private fun onClickRemoveButton() {
        binding.buttonRemoveTwoAnswers.setOnClickListener {
            audioViewModel.audio.playButtonSound(requireContext())
        }
    }


    override fun onPause() {
        super.onPause()
        gameMusic.pause()
    }

}