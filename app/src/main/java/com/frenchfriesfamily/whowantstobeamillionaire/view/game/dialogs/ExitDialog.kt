package com.frenchfriesfamily.whowantstobeamillionaire.view.game.dialogs

import androidx.navigation.fragment.findNavController
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.DialogExitBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.EventObserver
import com.frenchfriesfamily.whowantstobeamillionaire.view.AudioViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseDialogFragment
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.GameViewModel

class ExitDialog :
    BaseDialogFragment<DialogExitBinding, GameViewModel, AudioViewModel>(R.layout.dialog_exit) {

    override val viewModelClass = GameViewModel::class.java
    override val audioViewModelClass = AudioViewModel::class.java

    override fun onStart() {
        super.onStart()
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.apply {
            stayCLick.observe(this@ExitDialog, EventObserver { dismissDialog() })
            leaveClick.observe(this@ExitDialog, EventObserver { exitToHome() })
        }
    }

    private fun dismissDialog() {
        audioViewModel.audio.playButtonSound(requireContext())
        dismiss()
    }

    private fun exitToHome() {
        audioViewModel.audio.playButtonSound(requireContext())
        findNavController().popBackStack(R.id.homeFragment, false)
    }

}