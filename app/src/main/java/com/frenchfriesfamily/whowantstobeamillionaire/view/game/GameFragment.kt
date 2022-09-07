package com.frenchfriesfamily.whowantstobeamillionaire.view.game

import android.content.Context
import android.media.MediaPlayer
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentGameBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants
import com.frenchfriesfamily.whowantstobeamillionaire.utils.EventObserver
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
        observeEvents()
        onClickRemainingButtons()
        handleTimer()
    }

    private fun observeEvents() {
        viewModel.apply {
            audienceClick.observe(this@GameFragment, EventObserver { navToAudienceDialog() })
            callFriendClick.observe(this@GameFragment, EventObserver { navToCallFriendDialog() })
            exitCLick.observe(this@GameFragment, EventObserver { navToExitDialog() })
            gameOver.observe(this@GameFragment, EventObserver{navToResult()})
        }
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

        viewModel.seconds.observe(this) {
            if (it == Constants.TimeDurations.ZERO) {
                viewModel.stageCounter--
                navToResult()
            }
        }
    }


    private fun navToResult() {
        val currentStage = viewModel.getStageList()[viewModel.stageCounter]
        navigate(GameFragmentDirections.actionQuestionFragmentToResultFragment(currentStage))
    }

    private fun navToAudienceDialog() {
        playButtonSound()
        viewModel.onAskAudience(false)
        navigate(GameFragmentDirections.actionQuestionFragmentToAudienceDialog())
    }

    private fun navToCallFriendDialog() {
        playButtonSound()
        viewModel.onCallFriend(false)
        navigate(GameFragmentDirections.actionQuestionFragmentToFriendDialog())
    }

    private fun navToExitDialog() {
        playButtonSound()
        navigate(GameFragmentDirections.actionGameFragmentToExitDialog())
    }

    private fun onClickRemainingButtons() {
        binding.apply {
            buttonReplace.setOnClickListener { playButtonSound() }
            buttonRemove.setOnClickListener { playButtonSound() }
        }
    }

    private fun playButtonSound() = audioViewModel.audio.playButtonSound(requireContext())

    override fun onPause() {
        super.onPause()
        gameMusic.pause()
    }

}