package com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions

import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Event

fun MutableLiveData<Event<Boolean>>.postEvent() {
    this.postValue(Event(true))
}