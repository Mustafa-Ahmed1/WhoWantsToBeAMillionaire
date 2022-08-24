package com.frenchfriesfamily.whowantstobeamillionaire.view.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentAboutBinding

// TODO: use base fragment to reduce duplication
class AboutFragment : Fragment() {
    lateinit var binding: FragmentAboutBinding
    private val viewModel: AboutViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false)
        binding.apply {
            lifecycleOwner = this@AboutFragment
            viewModel = this@AboutFragment.viewModel
            return root
        }
    }
}