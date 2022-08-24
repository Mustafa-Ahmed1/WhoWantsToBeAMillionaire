package com.frenchfriesfamily.whowantstobeamillionaire.model.network

import com.frenchfriesfamily.whowantstobeamillionaire.model.response.QuestionsDto
import retrofit2.Response
import retrofit2.http.GET

interface QuestionsService {
    // TODO : make requests
    @GET("testing")
    fun getQuestions(): Response<QuestionsDto>
}