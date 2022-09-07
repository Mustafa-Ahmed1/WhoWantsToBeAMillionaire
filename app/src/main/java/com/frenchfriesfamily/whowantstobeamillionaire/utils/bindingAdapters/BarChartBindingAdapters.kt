package com.frenchfriesfamily.whowantstobeamillionaire.utils.bindingAdapters


import androidx.databinding.BindingAdapter
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.getColor
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.getProbableAudienceAnswers
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.Answer
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet

@BindingAdapter(value = ["app:audienceBarChartTheme"])
fun setUpBarChartTheme(view: BarChart, isActivated: Boolean?) {
    if (isActivated == true) {
        view.apply {
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

@BindingAdapter(value = ["app:correctAnswerAudience", "app:allAnswersAudience"])
fun setBareDataAsChart(view: BarChart, correctAnswer: String?, allAnswers: List<Answer?>?) {
    view.data = BarData(Constants.ANSWER_OPTIONS,
        BarDataSet(allAnswers.getProbableAudienceAnswers(correctAnswer), null)
            .apply {
                color = view.getColor(R.color.purple_lightest)
                valueTextColor = view.getColor(R.color.purple_lightest)
                valueTextSize = 10f
            }
    )
}