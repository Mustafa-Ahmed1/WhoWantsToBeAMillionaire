package com.frenchfriesfamily.whowantstobeamillionaire.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.utils.event.Event
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.postEvent
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel

class HomeViewModel : BaseViewModel() {

    private val _startClick = MutableLiveData<Event<Boolean>>()
    val startClick: LiveData<Event<Boolean>> = _startClick

    private val _aboutCLick = MutableLiveData<Event<Boolean>>()
    val aboutCLick: LiveData<Event<Boolean>> = _aboutCLick

    private val _exitClick = MutableLiveData<Event<Boolean>>()
    val exitClick: LiveData<Event<Boolean>> = _exitClick

    val onStartClicked = fun() = _startClick.postEvent()
    val onAboutClicked = fun() = _aboutCLick.postEvent()
    val onExitClicked = fun() = _exitClick.postEvent()

}