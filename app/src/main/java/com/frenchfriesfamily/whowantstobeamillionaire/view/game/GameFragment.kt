package com.frenchfriesfamily.whowantstobeamillionaire.view.game

import android.media.MediaPlayer
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentGameBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants
import com.frenchfriesfamily.whowantstobeamillionaire.utils.event.EventObserver
import com.frenchfriesfamily.whowantstobeamillionaire.view.AudioViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment

class GameFragment :
    BaseFragment<FragmentGameBinding, GameViewModel, AudioViewModel>(R.layout.fragment_game) {

    override val viewModelClass = GameViewModel::class.java
    override val audioViewModelClass = AudioViewModel::class.java

    private lateinit var gameMusic: MediaPlayer

    override fun onStart() {
        super.onStart()
        playGameSound()
        observeEvents()
        handleTimer()
    }

    private fun playGameSound() {
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
            audienceClick.observe(viewLifecycleOwner, EventObserver { navToAudienceDialog() })
            callFriendClick.observe(viewLifecycleOwner, EventObserver { navToCallFriendDialog() })
            exitCLick.observe(viewLifecycleOwner, EventObserver { navToExitDialog() })
            gameOver.observe(viewLifecycleOwner, EventObserver { navToResult() })
        }
    }

    // TODO: should be improved
    private fun handleTimer() {
        viewModel.question.observe(viewLifecycleOwner) {
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
        navigate(GameFragmentDirections.actionGameFragmentToResultFragment(currentStage))
    }

    private fun navToAudienceDialog() {
        viewModel.onAskAudience(false)
        navigate(GameFragmentDirections.actionGameFragmentToAudienceDialog())
    }

    private fun navToCallFriendDialog() {
        viewModel.onCallFriend(false)
        navigate(GameFragmentDirections.actionGameFragmentToFriendDialog())
    }

    private fun navToExitDialog() {
        navigate(GameFragmentDirections.actionGameFragmentToExitDialog())
    }


    override fun onPause() {
        super.onPause()
        gameMusic.pause()
    }

}