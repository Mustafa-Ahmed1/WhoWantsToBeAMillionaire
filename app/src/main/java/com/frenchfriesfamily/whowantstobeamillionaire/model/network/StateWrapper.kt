package com.frenchfriesfamily.whowantstobeamillionaire.model.network

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

object StateWrapper {
    fun <T> wrapWithObservable(requestFunction: () -> Single<Response<T>>): Observable<State<T>> =
        Observable.create { emitter ->
            Log.i("TESTING", "loading....")
            emitter.onNext(State.Loading)
            try {
                requestFunction().subscribe(
                    {
                        Log.i("TESTING", "success:  ${it}")
                        emitter.onNext(State.Success(it.body()))
                    }, {
                        Log.i("TESTING", "error: ${it.message.toString()}")
                        emitter.onNext(State.Error(it.message.toString()))
                    }
                )
            } catch (e: Exception) {
                Log.i("TESTING", "catched exception: ${e.message.toString()}")
                emitter.onNext(State.Error(e.message.toString()))
            }
        }
}