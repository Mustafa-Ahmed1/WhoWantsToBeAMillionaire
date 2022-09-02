package com.frenchfriesfamily.whowantstobeamillionaire.model.repositories

import com.frenchfriesfamily.whowantstobeamillionaire.model.network.State
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.GameResponse
import io.reactivex.rxjava3.core.Observable

interface GameRepository {
    fun getQuestions(
        amount: Int,
        level: String?,
        type: String?
    ): Observable<State<GameResponse>>
}