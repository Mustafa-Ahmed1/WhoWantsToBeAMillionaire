package com.frenchfriesfamily.whowantstobeamillionaire.view.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Event
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.postEvent
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel

class AboutViewModel : BaseViewModel() {

    private val _homeClick = MutableLiveData<Event<Boolean>>()
    val homeCLick: LiveData<Event<Boolean>> = _homeClick

    fun onBackToHomeClicked() = _homeClick.postEvent()

}