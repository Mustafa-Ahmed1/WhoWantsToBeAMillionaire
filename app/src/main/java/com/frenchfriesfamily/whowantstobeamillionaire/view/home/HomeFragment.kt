package com.frenchfriesfamily.whowantstobeamillionaire.view.home


import android.media.MediaPlayer
import androidx.navigation.Navigation
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentHomeBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.*
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment
import kotlin.system.exitProcess


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val viewModelClass = HomeViewModel::class.java
    lateinit var mediaPlayer: MediaPlayer
    override fun setUp() {
        navToQuestionFragment()
        navToAboutFragment()
        sound()
        exitApp()
        Audio.runAudio(mediaPlayer)
    }

    private fun navToQuestionFragment() {
        binding.buttonStart.setOnClickListener { view ->
            mediaPlayer.stop()
            Navigation.findNavController(view)
                .navigate(R.id.action_homeFragment_to_questionFragment)
        }
    }

    private fun navToAboutFragment() {
        binding.buttonAbout.setOnClickListener { view ->
            Audio.muteAudio(requireContext())

            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_aboutFragment)
        }
    }

    private fun sound() {
        mediaPlayer = MediaPlayer.create(this.context, R.raw.audio)
        binding.buttonSound.setOnClickListener {
            if (Audio.muteState == 100) {
                binding.buttonSound.setText(R.string.sounds_off)
                Audio.muteAudio(requireContext())

            } else {
                binding.buttonSound.setText(R.string.sounds_on)
                Audio.unmuteAudio(requireContext())
                Audio.runAudio(mediaPlayer)
            }
        }
    }

    private fun exitApp() {
        binding.buttonExit.setOnClickListener { exitProcess(-1) }
    }

}