package com.frenchfriesfamily.whowantstobeamillionaire.utils

import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.MINIMUM_PROBABILITY
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.Answer
import com.github.mikephil.charting.data.BarEntry
import kotlin.random.Random

class Probability {

    fun calculateProbability(answers: List<Answer?>?, correctAnswer: String): List<Float> {
        val probabilities = mutableListOf<Float>()
        answers?.forEach { answer ->
            when (answer?.answer) {
                correctAnswer -> probabilities.add(Random.nextFloat() + MINIMUM_PROBABILITY)
                else -> probabilities.add(Random.nextFloat())
            }
        }
        val sum = probabilities.sum()
        probabilities.map { probability ->
            probability / sum * 100
        }
        return probabilities
    }


    fun getProbableAnswerAsChar(probability: List<Float>): Char {
        return (probability.indexOf(probability.max()) + 65).toChar()
    }


    fun getProbableAudienceAnswers(
        correctAnswer: String?,
        answers: List<Answer?>?
    ): ArrayList<BarEntry> {
        val barEntries = arrayListOf<BarEntry>()
        val probabilities = calculateProbability(answers, correctAnswer.toString())
        probabilities.forEachIndexed { index, probability ->
            barEntries.add(BarEntry(probability, index))
        }
        return barEntries
    }
}