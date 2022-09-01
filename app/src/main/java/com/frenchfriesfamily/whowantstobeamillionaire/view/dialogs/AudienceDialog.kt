package com.frenchfriesfamily.whowantstobeamillionaire.view.dialogs

import android.media.MediaPlayer
import android.util.Log
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.DialogAudienceBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Audio
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseDialogFragment
import com.frenchfriesfamily.whowantstobeamillionaire.view.question.QuestionViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class AudienceDialog :
    BaseDialogFragment<DialogAudienceBinding, QuestionViewModel>(R.layout.dialog_audience) {
    override val viewModelClass = QuestionViewModel::class.java

    // y-axis bar chart data
    private val optionsValidityPercent = listOf<Float>(1f, 3f, 2f, .5f)
    private lateinit var barEntries: ArrayList<BarEntry>
    private lateinit var barDataSet: BarDataSet
    private lateinit var barData: BarData

    override fun onStart() {
        super.onStart()
        binding.buttonOk.setOnClickListener {
            Audio.runAudio(MediaPlayer.create(this.context, R.raw.push_audio))
            Log.i("TEST", "dismissing")
            this.dismiss()
        }

        setBarEntries(optionsValidityPercent)
        setBarDataSet(barEntries)
        barData = BarData(Constants.ANSWER_OPTIONS, barDataSet)
        setBarChartData(barData)
    }

    private fun setBarEntries(answerPercent: List<Float>) {
        barEntries = ArrayList()
        answerPercent.indices.forEach { i ->
            barEntries.add(BarEntry(optionsValidityPercent[i], i))
        }
    }

    private fun setBarDataSet(barEntry: ArrayList<BarEntry>) {
        barDataSet = BarDataSet(barEntry, "")
        barDataSet.apply {
            color = (resources.getColor(R.color.purple_lightest))
            valueTextColor = (resources.getColor(R.color.purple_lightest))
            valueTextSize = 15f
        }
    }

    private fun setBarChartData(barData: BarData) {
        binding.barChart.apply {
            data = barData
            setBackgroundColor(resources.getColor(R.color.purple))
            animateXY(2000, 2000)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.textSize = 12f
            xAxis.textColor = (resources.getColor(R.color.purple_lightest))
            xAxis.setDrawAxisLine(false)
            xAxis.setDrawGridLines(false)
            axisLeft.isEnabled = false
            axisRight.isEnabled = false
            setDescriptionPosition(0f, 0f)
            setTouchEnabled(false)
            setScaleEnabled(false)
            setPinchZoom(false)
            isDoubleTapToZoomEnabled = false
            legend.isEnabled = false
            invalidate()
        }
    }
}