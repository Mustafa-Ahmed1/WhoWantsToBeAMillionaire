package com.frenchfriesfamily.whowantstobeamillionaire.view.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentHomeBinding
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentQuestionBinding
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentResultBinding
import com.frenchfriesfamily.whowantstobeamillionaire.view.home.HomeViewModel

// TODO: use base fragment to reduce duplication
class ResultFragment : Fragment(){
    lateinit var binding: FragmentResultBinding
    private val viewModel: ResultsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        binding.apply {
            lifecycleOwner = this@ResultFragment
            viewModel = this@ResultFragment.viewModel
            return root
        }
    }
}