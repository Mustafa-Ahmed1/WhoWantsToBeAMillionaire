package com.frenchfriesfamily.whowantstobeamillionaire.view.question

import android.util.Log
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentQuestionBinding
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment
import com.frenchfriesfamily.whowantstobeamillionaire.view.dialogs.AudienceDialog
import com.frenchfriesfamily.whowantstobeamillionaire.view.dialogs.FriendDialog


class QuestionFragment :
    BaseFragment<FragmentQuestionBinding, QuestionViewModel>(R.layout.fragment_question) {

    override val viewModelClass = QuestionViewModel::class.java

    override fun setUp() {
        viewModel.startGame()
        viewModel.question.observe(this) {
            Log.i("kkk", it?.question.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        binding.apply {
            friendButton.setOnClickListener {
                showDialogFragment(FriendDialog())
            }
            audinceButton.setOnClickListener {
                showDialogFragment(AudienceDialog())
            }
        }
    }

    private fun showDialogFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction().add(fragment, "hello").commit()
    }

}