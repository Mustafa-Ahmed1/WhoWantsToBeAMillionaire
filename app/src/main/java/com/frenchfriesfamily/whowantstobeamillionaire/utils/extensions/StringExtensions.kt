package com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions

import org.jsoup.Jsoup
import kotlin.random.Random

fun String.replacePunctuationTextsWithSymbols(): String = Jsoup.parse(this).text()

fun String.getProbableAnswer(answers: List<String?>?): Char {
    var friendAnswer = 'A'
    val probabilities = mutableListOf<Float>()

    answers?.forEach { answer ->
        if (answer == this) {
            probabilities.add(Random.nextFloat() + 1f)
        } else {
            probabilities.add(Random.nextFloat())
        }
    }

    val maximumValue = probabilities.max()
    friendAnswer = (probabilities.indexOf(maximumValue) + 65).toChar()

    return friendAnswer
}
