package com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions

import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.utils.event.Event

fun MutableLiveData<Event<Boolean>>.postEvent() {
    this.postValue(Event(true))
}