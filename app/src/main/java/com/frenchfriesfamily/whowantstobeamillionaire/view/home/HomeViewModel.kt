package com.frenchfriesfamily.whowantstobeamillionaire.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Event
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.postEvent
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel

class HomeViewModel : BaseViewModel() {

    private val _startClick = MutableLiveData<Event<Boolean>>()
    val startClick: LiveData<Event<Boolean>> = _startClick

    private val _aboutCLick = MutableLiveData<Event<Boolean>>()
    val aboutCLick: LiveData<Event<Boolean>> = _aboutCLick

    private val _exitClick = MutableLiveData<Event<Boolean>>()
    val exitClick: LiveData<Event<Boolean>> = _exitClick

    fun onStartClicked() = _startClick.postEvent()
    fun onAboutClicked() = _aboutCLick.postEvent()
    fun onExitClicked() = _exitClick.postEvent()

}