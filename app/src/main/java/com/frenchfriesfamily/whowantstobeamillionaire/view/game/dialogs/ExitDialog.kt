package com.frenchfriesfamily.whowantstobeamillionaire.view.game.dialogs

import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.DialogExitBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.TIME_ABOUT_TO_DONE
import com.frenchfriesfamily.whowantstobeamillionaire.utils.event.EventObserver
import com.frenchfriesfamily.whowantstobeamillionaire.view.AudioViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseDialogFragment
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.GameViewModel

class ExitDialog :
    BaseDialogFragment<DialogExitBinding, GameViewModel, AudioViewModel>(R.layout.dialog_exit) {

    override val viewModelClass = GameViewModel::class.java
    override val audioViewModelClass = AudioViewModel::class.java

    override fun onStart() {
        super.onStart()
        onTimeDone()
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.apply {
            stayCLick.observe(viewLifecycleOwner, EventObserver { dismiss() })
            leaveClick.observe(viewLifecycleOwner, EventObserver { exitToHome() })
        }
    }

    private fun onTimeDone() {
        viewModel.seconds.observe(this) { if (it == TIME_ABOUT_TO_DONE) dismiss() }
    }


    private fun exitToHome() = popBackStack(R.id.homeFragment)

}