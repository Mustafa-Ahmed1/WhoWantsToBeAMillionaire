package com.frenchfriesfamily.whowantstobeamillionaire.view.game.dialogs

import android.media.MediaPlayer
import android.util.Log
import androidx.core.content.ContextCompat
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.DialogAudienceBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Audio
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseDialogFragment
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.GameViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlin.collections.ArrayList
import kotlin.random.Random

//TODO: clean up the mess
class AudienceDialog :
    BaseDialogFragment<DialogAudienceBinding, GameViewModel>(R.layout.dialog_audience) {
    override val viewModelClass = GameViewModel::class.java

    // y-axis bar chart data
    private lateinit var barData: BarData

    override fun onStart() {
        super.onStart()
        binding.buttonOk.setOnClickListener {
            Audio.runAudio(MediaPlayer.create(this.context, R.raw.push_audio))
            Log.i("TEST", "dismissing")
            this.dismiss()
        }
        viewModel.question.observe(this) {
            val barEntries = calculateProbabilityOfAnswers(it?.correctAnswer)
            val barDataSet = setBarDataSet(barEntries)

            barData = BarData(Constants.ANSWER_OPTIONS, barDataSet)
            setBarChartData(barData)

        }
    }


    //TODO: return different probability for right answer based on difficulity
    // if answer is difficult, the probability that the audince will get it will be lower
    // just create an enum and use it and change 1f to smaller number
    // if the number is higher, the less the probability of the correct answer

    //TODO: if possible, try to use binding adapters
    private fun calculateProbabilityOfAnswers(correctAnswer: String?): ArrayList<BarEntry> {
        val barEntries = arrayListOf<BarEntry>()
        viewModel.answers.observe(this) {
            val probabilites = mutableListOf<Float>()

            // TODO: implement algorithm in better way
            it?.forEach {  answer ->
                if (answer == correctAnswer) {
                    probabilites.add(Random.nextFloat() + 1f)
                } else {
                    probabilites.add(Random.nextFloat())
                }
            }

            val sum = probabilites.sum()

            probabilites.forEachIndexed { index, probability ->
                val barEntry = BarEntry(probability/sum*100,index)
                barEntries.add(barEntry)

            }

        }
        return barEntries
    }

    private fun setBarDataSet(barEntries: ArrayList<BarEntry>): BarDataSet {
        val barDataSet = BarDataSet(barEntries, "")
        barDataSet.apply {
            color = ContextCompat.getColor(requireContext(), R.color.purple_lightest)
            valueTextColor = ContextCompat.getColor(requireContext(), R.color.purple_lightest)
            valueTextSize = 10f
        }
        return barDataSet
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