package com.frenchfriesfamily.whowantstobeamillionaire.view.stage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frenchfriesfamily.whowantstobeamillionaire.model.data.StageDetails
import com.frenchfriesfamily.whowantstobeamillionaire.model.repositories.StagesRepository
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseInteractionListener
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseViewModel

class StageViewModel : BaseViewModel(), ProgressAdapter.StageInteractionListener {
    private val repository = StagesRepository()


    private val _stages = MutableLiveData<List<StageDetails>>()
    val stages: LiveData<List<StageDetails>>
        get() = _stages


    init {
        initStages()
    }

    private fun initStages() {
        val stageList = repository.getStages()
        _stages.postValue(stageList)
    }
}


