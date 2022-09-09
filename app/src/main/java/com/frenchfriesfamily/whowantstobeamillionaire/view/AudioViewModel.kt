package com.frenchfriesfamily.whowantstobeamillionaire.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Audio
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel

class AudioViewModel : BaseViewModel() {

    val audio = Audio()

    private val _isMuted = MutableLiveData(false)
    val isMuted: LiveData<Boolean> = _isMuted

    fun onButtonClicked() {
        when (_isMuted.value) {
            true -> _isMuted.postValue(false)
            else -> _isMuted.postValue(true)
        }
    }

}