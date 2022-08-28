package com.frenchfriesfamily.whowantstobeamillionaire.view.about

import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentAboutBinding
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment

class AboutFragment : BaseFragment<FragmentAboutBinding, AboutViewModel>(R.layout.fragment_about) {

    override val viewModelClass = AboutViewModel::class.java

    override fun setUp() {
    }

}