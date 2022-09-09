package com.frenchfriesfamily.whowantstobeamillionaire.view.home

import android.content.Context
import android.media.MediaPlayer
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentHomeBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.event.EventObserver
import com.frenchfriesfamily.whowantstobeamillionaire.view.AudioViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel, AudioViewModel>(R.layout.fragment_home) {

    override val viewModelClass = HomeViewModel::class.java
    override val audioViewModelClass = AudioViewModel::class.java

    lateinit var mediaPlayer: MediaPlayer

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mediaPlayer = MediaPlayer.create(context, R.raw.audio_home)
        mediaPlayer.start()
    }


    override fun onStart() {
        super.onStart()
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.apply {
            startClick.observe(viewLifecycleOwner, EventObserver { navToGame() })
            aboutCLick.observe(viewLifecycleOwner, EventObserver { navToAbout() })
            exitClick.observe(viewLifecycleOwner, EventObserver { exitApp() })
        }
    }

    private fun navToGame() = navigate(HomeFragmentDirections.actionHomeFragmentToGameFragment())

    private fun navToAbout() = navigate(HomeFragmentDirections.actionHomeFragmentToAboutFragment())

    private fun exitApp() = activity?.finish()


    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }

}