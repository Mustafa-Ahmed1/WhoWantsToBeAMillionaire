package com.frenchfriesfamily.whowantstobeamillionaire.view.base

import androidx.lifecycle.ViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.model.network.API
import com.frenchfriesfamily.whowantstobeamillionaire.model.network.StateWrapper
import com.frenchfriesfamily.whowantstobeamillionaire.model.repositories.GameRepositoryImpl
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val disposable = CompositeDisposable()

    protected val gameRepository = GameRepositoryImpl(API.apiService, StateWrapper())

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}