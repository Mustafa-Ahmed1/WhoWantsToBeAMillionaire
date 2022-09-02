package com.frenchfriesfamily.whowantstobeamillionaire.view.result

import androidx.navigation.fragment.navArgs
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentResultBinding
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment

class ResultFragment :
    BaseFragment<FragmentResultBinding, ResultsViewModel>(R.layout.fragment_result) {

    private val args: ResultFragmentArgs by navArgs()

    override val viewModelClass = ResultsViewModel::class.java

    override fun setUp() {
        binding.textLevel.text = args.stageDetails?.level.toString()
        binding.textMoney.text = args.stageDetails?.reward
    }

}