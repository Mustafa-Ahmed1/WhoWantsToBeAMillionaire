package com.frenchfriesfamily.whowantstobeamillionaire.view.dialogs

import android.util.Log
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.DialogFriendBinding
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseDialogFragment
import com.frenchfriesfamily.whowantstobeamillionaire.view.question.QuestionViewModel

class FriendDialog :
    BaseDialogFragment<DialogFriendBinding, QuestionViewModel>(R.layout.dialog_friend) {
    override val viewModelClass = QuestionViewModel::class.java

    override fun onStart() {
        super.onStart()
        binding.button.setOnClickListener {
            Log.i("TEST", "dismissing")
            dismiss()
        }
    }
}