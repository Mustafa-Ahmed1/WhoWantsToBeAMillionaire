package com.frenchfriesfamily.whowantstobeamillionaire.model.data

import java.io.Serializable

data class StageDetails(
    val reward: String,
    val level: Int
) : Serializable
