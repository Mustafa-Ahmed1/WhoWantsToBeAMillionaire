package com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions

import com.github.mikephil.charting.data.BarEntry
import org.jsoup.Jsoup
import kotlin.random.Random

fun String.replacePunctuationTextsWithSymbols(): String = Jsoup.parse(this).text()

fun String.getProbableAnswer(answers: List<String?>?): Char {
    val probabilities = mutableListOf<Float>()

    answers?.forEach { answer ->
        when (answer) {
            this -> probabilities.add(Random.nextFloat() + 1f)
            else -> probabilities.add(Random.nextFloat())
        }
    }

    val maximumValue = probabilities.max()

    return (probabilities.indexOf(maximumValue) + 65).toChar()
}

fun List<String?>?.getProbableAudienceAnswers(correctAnswer: String?): ArrayList<BarEntry> {
    val barEntries = arrayListOf<BarEntry>()
    val probabilities = mutableListOf<Float>()

    this?.forEach { answer ->
        when (answer) {
            correctAnswer -> probabilities.add(Random.nextFloat() + 1f)
            else -> probabilities.add(Random.nextFloat())
        }
    }

    val sum = probabilities.sum()
    probabilities.forEachIndexed { index, probability ->
        barEntries.add(BarEntry(probability / sum * 100, index))
    }

    return barEntries
}
