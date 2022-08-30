package com.frenchfriesfamily.whowantstobeamillionaire.view.result

import android.os.Bundle
import android.view.View
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentResultBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class ResultFragment :
    BaseFragment<FragmentResultBinding, ResultsViewModel>(R.layout.fragment_result) {

    override val viewModelClass = ResultsViewModel::class.java

    override fun setUp() {
    }

}