package com.frenchfriesfamily.whowantstobeamillionaire.view.question

import androidx.navigation.Navigation
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentQuestionBinding
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment


class QuestionFragment :
    BaseFragment<FragmentQuestionBinding, QuestionViewModel>(R.layout.fragment_question) {

    override val viewModelClass = QuestionViewModel::class.java

    override fun setUp() {
        val adapter = QuestionAdapter(mutableListOf(), viewModel)
        binding.recyclerAnswers.adapter = adapter



        binding.countdownView.apply {
            initTimer(15)
            startTimer()
        }
    }

    override fun onStart() {
        super.onStart()
        binding.apply {
            buttonCall.setOnClickListener { view ->
                Navigation.findNavController(view)
                    .navigate(R.id.action_questionFragment_to_friendDialog)
            }
            buttonAudience.setOnClickListener { view ->
                Navigation.findNavController(view)
                    .navigate(R.id.action_questionFragment_to_audienceDialog)
            }
        }
    }


}