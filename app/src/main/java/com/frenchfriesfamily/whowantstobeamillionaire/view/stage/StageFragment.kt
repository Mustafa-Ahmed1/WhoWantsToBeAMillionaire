package com.frenchfriesfamily.whowantstobeamillionaire.view.stage

import android.os.Bundle
import android.view.View
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentStageBinding
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment

class StageFragment :
    BaseFragment<FragmentStageBinding, StageViewModel>(R.layout.fragment_stage) {
    override val viewModelClass: Class<StageViewModel> = StageViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ProgressAdapter(mutableListOf(), viewModel)
        binding.recyclerViewMoney.adapter = adapter
    }
}

