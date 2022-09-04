package com.frenchfriesfamily.whowantstobeamillionaire.view.stage

import androidx.navigation.Navigation
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentStageBinding
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.AudioViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment

class StageFragment :
    BaseFragment<FragmentStageBinding, StageViewModel ,AudioViewModel>(R.layout.fragment_stage) {

    override val viewModelClass: Class<StageViewModel> = StageViewModel::class.java
    override val audioViewModelClass = AudioViewModel::class.java

    override fun setUp() {
        val adapter = StagesAdapter(mutableListOf(), viewModel)
        binding.recyclerStages.adapter = adapter

        navToQuestionFragment()
    }

    private fun navToQuestionFragment() {
        binding.textTapToContinue.setOnClickListener { view ->
            Navigation.findNavController(view).popBackStack()
        }
    }

}