package com.frenchfriesfamily.whowantstobeamillionaire.view.question

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
import com.frenchfriesfamily.whowantstobeamillionaire.view.home.HomeViewModel

class QuestionFragment : Fragment() {
    lateinit var binding: FragmentQuestionBinding
    private val viewModel: QuestionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        binding.apply {
            lifecycleOwner = this@QuestionFragment
            viewModel = this@QuestionFragment.viewModel
            return root
        }
    }
}