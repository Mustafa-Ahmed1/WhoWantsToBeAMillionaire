package com.frenchfriesfamily.whowantstobeamillionaire.view.stage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.model.data.StageDetails
import com.frenchfriesfamily.whowantstobeamillionaire.model.repositories.StagesRepository
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel

class StageViewModel : BaseViewModel(), StageInteractionListener {
    private val repository = StagesRepository()


    private val _stages = MutableLiveData<List<StageDetails>>()
    val stages: LiveData<List<StageDetails>>
        get() = _stages

    init {
        getStages()
    }

    private fun getStages() {
        val stageList = repository.getStage()
        _stages.postValue(stageList)
    }
}


