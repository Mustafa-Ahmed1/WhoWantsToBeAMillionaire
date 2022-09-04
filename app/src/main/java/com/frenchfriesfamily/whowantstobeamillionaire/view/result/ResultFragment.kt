package com.frenchfriesfamily.whowantstobeamillionaire.view.result

import androidx.navigation.fragment.navArgs
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentResultBinding
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.AudioViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment

class ResultFragment :
    BaseFragment<FragmentResultBinding, ResultsViewModel,AudioViewModel>(R.layout.fragment_result) {
    override val viewModelClass = ResultsViewModel::class.java
    override val audioViewModelClass = AudioViewModel::class.java
    private val args: ResultFragmentArgs by navArgs()

    override fun setUp() {
        binding.textLevel.text = args.stageDetails?.level.toString()
        binding.textMoney.text = args.stageDetails?.reward
    }

}