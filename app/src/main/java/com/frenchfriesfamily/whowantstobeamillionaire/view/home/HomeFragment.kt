package com.frenchfriesfamily.whowantstobeamillionaire.view.home

import android.content.Context
import android.media.MediaPlayer
import androidx.navigation.Navigation
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentHomeBinding
import com.frenchfriesfamily.whowantstobeamillionaire.view.AudioViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment
import kotlin.system.exitProcess

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel, AudioViewModel>(R.layout.fragment_home) {

    override val viewModelClass = HomeViewModel::class.java
    override val audioViewModelClass = AudioViewModel::class.java

    private lateinit var homeMusic: MediaPlayer

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeMusic = MediaPlayer.create(context, R.raw.home_audio)
        homeMusic.start()
    }

    override fun setUp() {
        onClickButtons()
    }

    private fun onClickButtons() {
        navToQuestionFragment()
        navToAboutFragment()
        exitApp()
    }

    private fun navToQuestionFragment() {
        binding.buttonStart.setOnClickListener { view ->
            audioViewModel.audio.playButtonSound(requireContext())
            val action = HomeFragmentDirections.actionHomeFragmentToQuestionFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    private fun navToAboutFragment() {
        binding.buttonAbout.setOnClickListener { view ->
            audioViewModel.audio.playButtonSound(requireContext())
            val action = HomeFragmentDirections.actionHomeFragmentToAboutFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    // TODO: should destroy activity, not just pause it
    private fun exitApp() {
        binding.buttonExit.setOnClickListener { exitProcess(-1) }
    }

    override fun onPause() {
        super.onPause()
        homeMusic.pause()
    }

}