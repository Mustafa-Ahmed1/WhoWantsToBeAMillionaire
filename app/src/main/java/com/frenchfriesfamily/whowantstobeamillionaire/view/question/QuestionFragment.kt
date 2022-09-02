package com.frenchfriesfamily.whowantstobeamillionaire.view.question

import android.util.Log
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentQuestionBinding
import com.frenchfriesfamily.whowantstobeamillionaire.model.AnswerState
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment

class QuestionFragment :
    BaseFragment<FragmentQuestionBinding, QuestionViewModel>(R.layout.fragment_question) {

    override val viewModelClass = QuestionViewModel::class.java

    override fun setUp() {
        viewModel.question.observe(this) {
            Log.i("kkk", it?.question.toString())
        }

        val adapter = QuestionAdapter(mutableListOf(), viewModel)
        binding.recyclerAnswers.adapter = adapter

        binding.countdownView.apply {
            initTimer(15)
            startTimer()
        }

        viewModel.question.observe(this) {
            binding.countdownView.apply {
                initTimer(15)
                startTimer()
            }
        }
        viewModel.answerState.observe(this) { state ->
            binding.countdownView.apply {
                if (state != AnswerState.IS_DEFAULT) {
                    pauseTimer()
                }
            }
            if (state == AnswerState.IS_WRONG) {
                binding.root.findNavController()
                    .navigate(R.id.action_questionFragment_to_resultFragment)
            }
            if (state == AnswerState.IS_CORRECT) {
                binding.root.findNavController()
                    .navigate(R.id.action_questionFragment_to_stageFragment)
            }
        }
        viewModel.seconds.observe(this) {
            if (it == 0) {
                //TODO: add navigation with nav args to results fragment
            }
        }
        viewModel.questionsList.observe(this) {

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
                viewModel?.onCallFriend(false)
                val action = QuestionFragmentDirections.actionQuestionFragmentToFriendDialog()
                Navigation.findNavController(view).navigate(action)
            }
            buttonAudience.setOnClickListener { view ->
                viewModel?.onAskAudience(false)
                val action = QuestionFragmentDirections.actionQuestionFragmentToAudienceDialog()
                Navigation.findNavController(view).navigate(action)
            }
        }
    }

}