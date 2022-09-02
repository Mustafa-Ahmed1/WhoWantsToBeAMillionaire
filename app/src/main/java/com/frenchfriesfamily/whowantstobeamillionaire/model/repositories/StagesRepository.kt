package com.frenchfriesfamily.whowantstobeamillionaire.model.repositories

import com.frenchfriesfamily.whowantstobeamillionaire.model.data.StageDetails

class StagesRepository {

    fun getStages() = listOf(
        StageDetails("$ 1,000,000", 15),
        StageDetails("$ 500,000", 14),
        StageDetails("$ 250,000", 13),
        StageDetails("$ 100,000", 12),
        StageDetails("$ 50,000", 11),
        StageDetails("$ 25,000", 10),
        StageDetails("$ 15,000", 9),
        StageDetails("$ 12,500", 8),
        StageDetails("$ 10,000", 7),
        StageDetails("$ 7,500", 6),
        StageDetails("$ 5,000", 5),
        StageDetails("$ 3,000", 4),
        StageDetails("$ 2,000", 3),
        StageDetails("$ 1,000", 2),
        StageDetails("$ 500", 1),
        StageDetails("$ 0", 0)
    )

}