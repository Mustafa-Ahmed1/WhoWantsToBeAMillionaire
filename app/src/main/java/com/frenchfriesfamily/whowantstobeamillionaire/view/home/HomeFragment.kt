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
            Navigation.findNavController(view)
                .navigate(R.id.action_homeFragment_to_questionFragment)
        }
    }

    private fun navToAboutFragment() {
        binding.buttonAbout.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_aboutFragment)
        }
    }

    private fun exitApp() {
        binding.buttonExit.setOnClickListener { exitProcess(-1) }
    }

}