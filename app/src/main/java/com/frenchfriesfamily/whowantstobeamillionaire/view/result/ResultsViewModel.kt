package com.frenchfriesfamily.whowantstobeamillionaire.view.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Event
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.postEvent
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel

class ResultsViewModel : BaseViewModel() {

    private val _homeClick = MutableLiveData<Event<Boolean>>()
    val homeCLick: LiveData<Event<Boolean>> = _homeClick

    private val _gameClick = MutableLiveData<Event<Boolean>>()
    val gameClick: LiveData<Event<Boolean>> = _gameClick


    fun onBackToHomeClicked() = _homeClick.postEvent()
    fun onPlayAgainClicked() = _gameClick.postEvent()

}