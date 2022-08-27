package com.frenchfriesfamily.whowantstobeamillionaire.model.repositories

import android.util.Log
import com.frenchfriesfamily.whowantstobeamillionaire.model.network.API
import com.frenchfriesfamily.whowantstobeamillionaire.model.network.State
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.QuestionsDto
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.local.LocalResponse
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.convertToLocalResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response


class QuestionsRepository {

    private val api = API.apiService

    fun getQuestioneList(
        amount: Int,
        level: String?,
        type: String?
    ): Single<State.Success<LocalResponse>> {

        return wrap(api.getQuestions(amount, level, type)).map {
            State.Success(it.toData()?.convertToLocalResponse())
        }
    }

    private fun <T> wrap(response: Single<Response<T>>): Single<State<T>> {
        return response.map {
            State.Success(it.body())
        }
    }


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