package com.frenchfriesfamily.whowantstobeamillionaire.view.dialogs

import android.util.Log
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.DialogAudiunceBinding
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseDialogFragment
import com.frenchfriesfamily.whowantstobeamillionaire.view.question.QuestionViewModel

class AudienceDialog :
    BaseDialogFragment<DialogAudiunceBinding, QuestionViewModel>(R.layout.dialog_audiunce) {
    override val viewModelClass = QuestionViewModel::class.java


    override fun onStart() {
        super.onStart()
        binding.buttonOk.setOnClickListener {
            Log.i("TEST", "dismissing")
            this.dismiss()
        }
    }
}