package com.frenchfriesfamily.whowantstobeamillionaire.view.home


import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentHomeBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.*
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.AudioViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment
import kotlin.system.exitProcess

// TODO: clean the mess
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel,AudioViewModel>(R.layout.fragment_home) {
    override val viewModelClass = HomeViewModel::class.java
    override val audioViewModelClass = AudioViewModel::class.java

    lateinit var mediaPlayer: MediaPlayer
    private val audioInHomeFragment = Audio()

    override fun onPause() {
        super.onPause()
        audioInHomeFragment.pauseAudio(mediaPlayer)
    }


    override fun setUp() {
        navToQuestionFragment()
        navToAboutFragment()
        sound()
        exitApp()
        audioInHomeFragment.runAudio(mediaPlayer)

    }

    private fun navToQuestionFragment() {
        binding.buttonStart.setOnClickListener { view ->
            mediaPlayer.stop()

            val action = HomeFragmentDirections.actionHomeFragmentToQuestionFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    private fun navToAboutFragment() {
        binding.buttonAbout.setOnClickListener { view ->
            audioInHomeFragment.muteAudio(requireContext())

            val action = HomeFragmentDirections.actionHomeFragmentToAboutFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    private fun sound() {
        mediaPlayer = MediaPlayer.create(this.context, R.raw.home_audio)

        binding.buttonSound.setOnClickListener {
            if (audioInHomeFragment.muteState) {
                binding.buttonSound.setText(R.string.sounds_off)
                audioInHomeFragment.muteAudio(requireContext())

            } else {
                binding.buttonSound.setText(R.string.sounds_on)
                audioInHomeFragment.unMuteAudio(requireContext())
                audioInHomeFragment.runAudio(mediaPlayer)
            }
        }
    }

    private fun exitApp() {
        binding.buttonExit.setOnClickListener { exitProcess(-1) }
    }

}