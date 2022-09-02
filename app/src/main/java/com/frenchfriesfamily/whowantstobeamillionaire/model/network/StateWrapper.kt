package com.frenchfriesfamily.whowantstobeamillionaire.model.network

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class StateWrapper {
    fun <T> wrapWithObservable(requestFunction: () -> Single<Response<T>>): Observable<State<T>> =
        Observable.create { emitter ->
            emitter.onNext(State.Loading)
            try {
                requestFunction().subscribe(
                    {
                        emitter.onNext(State.Success(it.body()))
                    }, {
                        emitter.onNext(State.Error(it.message.toString()))
                    }
                )
            } catch (e: Exception) {
                emitter.onNext(State.Error(e.message.toString()))
            }
        }
}