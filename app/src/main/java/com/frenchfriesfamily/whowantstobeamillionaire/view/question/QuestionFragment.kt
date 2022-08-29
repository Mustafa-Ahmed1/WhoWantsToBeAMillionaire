package com.frenchfriesfamily.whowantstobeamillionaire.view.question

import android.util.Log
import androidx.navigation.Navigation
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentQuestionBinding
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment


class QuestionFragment :
    BaseFragment<FragmentQuestionBinding, QuestionViewModel>(R.layout.fragment_question) {

    override val viewModelClass = QuestionViewModel::class.java

    override fun setUp() {
        viewModel.question.observe(this) {
            Log.i("kkk", it?.question.toString())
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