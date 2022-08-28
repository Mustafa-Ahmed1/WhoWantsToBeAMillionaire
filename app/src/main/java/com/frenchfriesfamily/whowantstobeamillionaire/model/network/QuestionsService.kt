package com.frenchfriesfamily.whowantstobeamillionaire.model.network

import com.frenchfriesfamily.whowantstobeamillionaire.model.response.QuestionResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionsService {

    @GET("api.php")
    fun getQuestions(
        @Query("amount") amountKey: Int,
        @Query("difficulty") difficultyKey: String?,
        @Query("type") typeKey: String?,
    ): Single<Response<QuestionResponse>>

}