package com.frenchfriesfamily.whowantstobeamillionaire.view.home

import android.content.Context
import android.media.MediaPlayer
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentHomeBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.EventObserver
import com.frenchfriesfamily.whowantstobeamillionaire.view.AudioViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment

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
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.apply {
            startClick.observe(this@HomeFragment, EventObserver { navToGame() })
            aboutCLick.observe(this@HomeFragment, EventObserver { navToAbout() })
            exitClick.observe(this@HomeFragment, EventObserver { exitApp() })
        }
    }

    private fun navToGame() {
        audioViewModel.audio.playButtonSound(requireContext())
        navigate(HomeFragmentDirections.actionHomeFragmentToQuestionFragment())
    }

    private fun navToAbout() {
        audioViewModel.audio.playButtonSound(requireContext())
        navigate(HomeFragmentDirections.actionHomeFragmentToAboutFragment())
    }

    private fun exitApp() {
        audioViewModel.audio.playButtonSound(requireContext())
        activity?.finish()
    }


    override fun onPause() {
        super.onPause()
        homeMusic.pause()
    }

}