package com.frenchfriesfamily.whowantstobeamillionaire.view.result

import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentResultBinding
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment


class ResultFragment : BaseFragment<FragmentResultBinding, ResultsViewModel>(R.layout.fragment_result) {
    override val viewModelClass = ResultsViewModel::class.java
    override fun setUp() {
    }

}