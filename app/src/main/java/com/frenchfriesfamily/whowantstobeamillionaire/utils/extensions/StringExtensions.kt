package com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions

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
