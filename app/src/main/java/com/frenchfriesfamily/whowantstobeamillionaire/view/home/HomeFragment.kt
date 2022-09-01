package com.frenchfriesfamily.whowantstobeamillionaire.view.home

import androidx.navigation.Navigation
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentHomeBinding
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment
import kotlin.system.exitProcess

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val viewModelClass = HomeViewModel::class.java

    override fun setUp() {
        navToQuestionFragment()
        navToAboutFragment()
        exitApp()
    }

    private fun navToQuestionFragment() {
        binding.buttonStart.setOnClickListener { view ->
            val action = HomeFragmentDirections.actionHomeFragmentToQuestionFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    private fun navToAboutFragment() {
        binding.buttonAbout.setOnClickListener { view ->
            val action = HomeFragmentDirections.actionHomeFragmentToAboutFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    private fun exitApp() {
        binding.buttonExit.setOnClickListener { exitProcess(-1) }
    }

}