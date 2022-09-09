package com.frenchfriesfamily.whowantstobeamillionaire.view.result

import android.media.MediaPlayer
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentResultBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.LAST_LEVEL
import com.frenchfriesfamily.whowantstobeamillionaire.utils.event.EventObserver
import com.frenchfriesfamily.whowantstobeamillionaire.view.AudioViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment

class ResultFragment :
    BaseFragment<FragmentResultBinding, ResultsViewModel, AudioViewModel>(R.layout.fragment_result) {

    override val viewModelClass = ResultsViewModel::class.java
    override val audioViewModelClass = AudioViewModel::class.java

    lateinit var mediaPlayer: MediaPlayer

    private val args: ResultFragmentArgs by navArgs()

    override fun onStart() {
        super.onStart()
        args.stageDetails?.let { viewModel.getCurrentStage(it) }
        observeEvents()
        handleOnBackPressed()
        playResultSound()
    }

    private fun handleOnBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navToHome()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun observeEvents() {
        viewModel.apply {
            homeCLick.observe(viewLifecycleOwner, EventObserver { navToHome() })
            gameClick.observe(viewLifecycleOwner, EventObserver { navToGame() })
        }
    }

    private fun navToHome() = popBackStack(R.id.homeFragment)

    private fun navToGame() = popBackStack()


    private fun playResultSound() {
        mediaPlayer = when (args.stageDetails?.level) {
            LAST_LEVEL -> MediaPlayer.create(context, R.raw.audio_winning)
            else -> MediaPlayer.create(context, R.raw.audio_losing)
        }
        mediaPlayer.start()
    }


    override fun onPause() {
        super.onPause()
        mediaPlayer.stop()
    }

}