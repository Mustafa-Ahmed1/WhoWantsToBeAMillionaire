package com.frenchfriesfamily.whowantstobeamillionaire.utils

import com.frenchfriesfamily.whowantstobeamillionaire.utils.ProbabilityFunctionsConstants.CORRECT_ANSWER_PROBABILITY

fun main() {

    // should contain four answers, first one is always the correct answer
    val answers = listOf("A", "B", "C", "D")

    // test "call a friend" function 10 times
    for (i in 1..10) {
        println(getAnswerByProbability(answers, CORRECT_ANSWER_PROBABILITY))
    }

    println("---------------------------------")

    // test "audience answers" function
    println(getAnswersProbabilities(answers))

}

fun getAnswerByProbability(list: List<String>, correctAnswerProbability: Int): String {
    val randomPercentage = (0..100).random()
    val correctAnswer = list[0]
    val randomIncorrectAnswer = list[(1 until list.size).random()]

    return if (randomPercentage <= correctAnswerProbability) correctAnswer
    else randomIncorrectAnswer
}

fun getAnswersProbabilities(list: List<String>): MutableList<Int> {
    val probabilities = mutableListOf<Int>()

    val correctAnswerProbability = (40..60).random()
    probabilities.add(correctAnswerProbability)

    for (i in 1 until list.size) {
        val randomIncorrectAnswerProbability = (0..50).random()
        probabilities.add(randomIncorrectAnswerProbability)
    }

    return probabilities
}

object ProbabilityFunctionsConstants {
    const val CORRECT_ANSWER_PROBABILITY = 70
}