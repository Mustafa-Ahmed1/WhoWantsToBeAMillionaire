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

        binding.countdownView.apply {
            initTimer(15)
            startTimer()
        }

        navToHomeFragment()
        navToStageFragment()
        onClickDialogs()
    }

    private fun navToStageFragment() {
        binding.buttonRemoveTwoAnswers.setOnClickListener { view ->
            val action = QuestionFragmentDirections.actionQuestionFragmentToStageFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    private fun navToHomeFragment() {
        binding.buttonLeave.setOnClickListener { view ->
            Navigation.findNavController(view)
                .popBackStack()
        }
    }

    private fun onClickDialogs() {
        binding.apply {
            buttonCall.setOnClickListener { view ->
                val action =
                    QuestionFragmentDirections.actionQuestionFragmentToFriendDialog()
                Navigation.findNavController(view).navigate(action)
            }
            buttonAudience.setOnClickListener { view ->
                val action =
                    QuestionFragmentDirections.actionQuestionFragmentToAudienceDialog()
                Navigation.findNavController(view).navigate(action)
            }
        }
    }

}