package com.frenchfriesfamily.whowantstobeamillionaire.view.question

import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentQuestionBinding
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment

class QuestionFragment :
    BaseFragment<FragmentQuestionBinding, QuestionViewModel>(R.layout.fragment_question) {
    override val viewModelClass = QuestionViewModel::class.java

}