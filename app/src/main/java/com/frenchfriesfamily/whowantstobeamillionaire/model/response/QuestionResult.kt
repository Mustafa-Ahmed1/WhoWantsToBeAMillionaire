package com.frenchfriesfamily.whowantstobeamillionaire.model.response

import com.google.gson.annotations.SerializedName

data class QuestionResult(
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("correct_answer")
    val correctAnswer: String? = null,
    @SerializedName("difficulty")
    val difficulty: String? = null,
    @SerializedName("incorrect_answers")
    val incorrectAnswers: List<String?>? = null,
    @SerializedName("question")
    val question: String? = null,
    @SerializedName("type")
    val type: String? = null
)