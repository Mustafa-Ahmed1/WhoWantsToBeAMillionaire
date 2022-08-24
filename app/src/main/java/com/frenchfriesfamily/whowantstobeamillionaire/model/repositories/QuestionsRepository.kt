package com.frenchfriesfamily.whowantstobeamillionaire.model.repositories

import com.frenchfriesfamily.whowantstobeamillionaire.model.network.API
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.QuestionsDto

class QuestionsRepository {

    private val api = API.apiService

    fun getQuestions(): List<QuestionsDto> {
        // TODO : get data for network
        return listOf(
            QuestionsDto(1,"fake data at first to check!"),
            QuestionsDto(2,"do you understand the code?"),
            QuestionsDto(3,"you can ask for help if you don't understand"),
            QuestionsDto(4,"please note that this class is fake too"),
            QuestionsDto(5,"you can improve the naming"),
            QuestionsDto(6,"please don't commit if the code is not working"),
            QuestionsDto(7,"go to nav graph and set the fragment you're working on as the home fragment to test it"),
            QuestionsDto(8,"don't edit code on other layers unless you tell others")
        )
    }
}