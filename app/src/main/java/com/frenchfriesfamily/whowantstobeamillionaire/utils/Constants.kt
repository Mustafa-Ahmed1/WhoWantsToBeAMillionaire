package com.frenchfriesfamily.whowantstobeamillionaire.utils

//TODO : clean up the mess
object Constants {
    const val BASE_URL = "https://opentdb.com/"

    // x-axis bar chart data
    val ANSWER_OPTIONS = listOf("A", "B", "C", "D")

    val DIFFICULTY = listOf("easy", "medium", "hard")
    const val AMOUNT_OF_QUESTION = 6
    const val QUESTION_TYPE = "multiple"

    object TimeDurations {
        const val MAX_DURATION = 15
        const val SECOND_THIRD_DURATION = 10
        const val FIRST_THIRD_DURATION = 5
        const val ZERO = 0
    }

}