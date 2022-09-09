package com.frenchfriesfamily.whowantstobeamillionaire.view.about

import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentAboutBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.event.EventObserver
import com.frenchfriesfamily.whowantstobeamillionaire.view.AudioViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment

class AboutFragment :
    BaseFragment<FragmentAboutBinding, AboutViewModel, AudioViewModel>(R.layout.fragment_about) {

    override val viewModelClass = AboutViewModel::class.java
    override val audioViewModelClass = AudioViewModel::class.java

    override fun observeEvents() {
        viewModel.homeCLick.observe(this, EventObserver { navToHome() })
    }

    private fun navToHome() {
        popBackStack()
    }

}