package com.frenchfriesfamily.whowantstobeamillionaire.model.network

import com.frenchfriesfamily.whowantstobeamillionaire.model.response.QuestionResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.*


interface QuestionsService {
    // TODO : make requests

    @GET("api.php")
    fun getQuestions(
        @Query("amount") amountKey: Int,
        @Query("category") categoryKey: Int?,
        @Query("difficulty") difficultyKey: String?,
        @Query("type") typeKey: String?,
    ): Single<Response<QuestionResponse>>

}