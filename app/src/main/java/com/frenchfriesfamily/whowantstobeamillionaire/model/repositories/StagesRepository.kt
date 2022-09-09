package com.frenchfriesfamily.whowantstobeamillionaire.model.repositories

import com.frenchfriesfamily.whowantstobeamillionaire.model.data.StageDetails

interface StagesRepository {
    fun getStages(): List<StageDetails>
}