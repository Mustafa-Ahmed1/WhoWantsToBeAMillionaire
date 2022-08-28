package com.frenchfriesfamily.whowantstobeamillionaire.view.home

import androidx.navigation.Navigation
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentHomeBinding
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val viewModelClass = HomeViewModel::class.java

    override fun setUp() {
        navToQuestionFragment()
    }

    private fun navToQuestionFragment() {
        binding.startBtn.setOnClickListener { v ->
            Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_questionFragment)
        }
    }

}