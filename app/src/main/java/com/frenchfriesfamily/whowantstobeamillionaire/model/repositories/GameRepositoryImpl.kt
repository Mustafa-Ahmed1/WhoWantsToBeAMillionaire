package com.frenchfriesfamily.whowantstobeamillionaire.model.repositories

import com.frenchfriesfamily.whowantstobeamillionaire.model.network.GameService
import com.frenchfriesfamily.whowantstobeamillionaire.model.network.State
import com.frenchfriesfamily.whowantstobeamillionaire.model.network.StateWrapper
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.GameResponse
import io.reactivex.rxjava3.core.Observable


class GameRepositoryImpl(
    private val api: GameService,
    private val stateWrapper: StateWrapper
): GameRepository {
    override fun getQuestions(
        amount: Int,
        level: String?,
        type: String?
    ): Observable<State<GameResponse>> {
        return stateWrapper.wrapWithObservable { api.getQuestions(amount, level, type) }
    }
}