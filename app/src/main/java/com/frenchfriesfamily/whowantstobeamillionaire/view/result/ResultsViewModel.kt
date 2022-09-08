package com.frenchfriesfamily.whowantstobeamillionaire.view.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.model.data.StageDetails
import com.frenchfriesfamily.whowantstobeamillionaire.utils.event.Event
import com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions.postEvent
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel

class ResultsViewModel : BaseViewModel() {

    private val _homeClick = MutableLiveData<Event<Boolean>>()
    val homeCLick: LiveData<Event<Boolean>> = _homeClick

    private val _gameClick = MutableLiveData<Event<Boolean>>()
    val gameClick: LiveData<Event<Boolean>> = _gameClick

    private val _stage = MutableLiveData<StageDetails>()
    val stage: LiveData<StageDetails> = _stage


    fun onBackToHomeClicked() = _homeClick.postEvent()
    fun onPlayAgainClicked() = _gameClick.postEvent()

    fun getCurrentStage(currentStage: StageDetails) = _stage.postValue(currentStage)

}