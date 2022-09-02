package com.frenchfriesfamily.whowantstobeamillionaire.model.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("response_code")
    val responseCode: Int? = null,
    @SerializedName("results")
    val results: List<Question>? = null
)