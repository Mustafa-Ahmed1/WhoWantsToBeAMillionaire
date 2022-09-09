package com.frenchfriesfamily.whowantstobeamillionaire.view.game

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentGameBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.event.EventObserver
import com.frenchfriesfamily.whowantstobeamillionaire.view.AudioViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment

class GameFragment :
    BaseFragment<FragmentGameBinding, GameViewModel, AudioViewModel>(R.layout.fragment_game) {

    override val viewModelClass = GameViewModel::class.java
    override val audioViewModelClass = AudioViewModel::class.java

    private lateinit var mediaPlayer: MediaPlayer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startGame()
        handleOnBackPressed()

    }

    override fun observeEvents() {
        viewModel.apply {
            audienceClick.observe(viewLifecycleOwner, EventObserver { navToAudienceDialog() })
            callFriendClick.observe(viewLifecycleOwner, EventObserver { navToCallFriendDialog() })
            exitCLick.observe(viewLifecycleOwner, EventObserver { navToExitDialog() })
            gameOver.observe(viewLifecycleOwner, EventObserver { navToResult() })
        }
    }

    private fun handleOnBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() = navToExitDialog()
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }

    private fun startGame() {
        viewModel.resetGameData()
        playGameSound()
        viewModel.endGameWhenTimeIsUp()
    }


    private fun navToResult() {
        val currentStage = viewModel.getStageList()[viewModel.currentStage]
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


    private fun playGameSound() {
        mediaPlayer = MediaPlayer.create(context, R.raw.audio_game)
        mediaPlayer.start()
    }


    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }

}