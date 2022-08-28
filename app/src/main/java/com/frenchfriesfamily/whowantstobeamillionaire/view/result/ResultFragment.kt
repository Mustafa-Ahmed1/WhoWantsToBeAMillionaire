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


class ResultFragment : BaseFragment<FragmentResultBinding, ResultsViewModel>(R.layout.fragment_result) {
    override val viewModelClass = ResultsViewModel::class.java
    override fun setUp() {
    }

    // y-axis bar chart data
    private val optionsValidityPercent = listOf<Float>(1f,3f,2f,.5f)
    private lateinit var barEntries:ArrayList<BarEntry>
    private lateinit var barDataSet: BarDataSet
    private lateinit var barData: BarData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBarEntries(optionsValidityPercent)
        setBarDataSet(barEntries)
        barData = BarData(Constants.ANSWER_OPTIONS, barDataSet)
        setBarChartData(barData)
    }

    private fun setBarEntries(answerPercent: List<Float>){
        barEntries = ArrayList()
        answerPercent.indices.forEach { i ->
            barEntries.add(BarEntry(optionsValidityPercent[i], i))
        }
    }

    private fun setBarDataSet(barEntry: ArrayList<BarEntry>){
        barDataSet = BarDataSet(barEntry,"Audience Answer")
        barDataSet.apply {
            color = (resources.getColor(R.color.purple_light))
            valueTextColor = (resources.getColor(R.color.purple))
            valueTextSize = 15f
        }
    }

    private fun setBarChartData(barData: BarData){
        binding.barChart.apply {
            data = barData
            setBackgroundColor(resources.getColor(R.color.white_87))
            animateXY(2000, 2000)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.textSize = 15f
            xAxis.textColor = (resources.getColor(R.color.purple))
            xAxis.setDrawAxisLine(false)
            xAxis.setDrawGridLines(false)
            axisLeft.isEnabled = false
            axisRight.isEnabled = false
            setDescriptionPosition(0f, 0f)
            setTouchEnabled(false)
            setScaleEnabled(false)
            setPinchZoom(false)
            isDoubleTapToZoomEnabled = false
            invalidate()
        }
    }

}