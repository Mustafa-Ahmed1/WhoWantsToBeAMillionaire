package com.frenchfriesfamily.whowantstobeamillionaire.view.game.dialogs

import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.DialogFriendBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.EventObserver
import com.frenchfriesfamily.whowantstobeamillionaire.view.AudioViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseDialogFragment
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.GameViewModel

class FriendDialog :
    BaseDialogFragment<DialogFriendBinding, GameViewModel, AudioViewModel>(R.layout.dialog_friend) {

    override val viewModelClass = GameViewModel::class.java
    override val audioViewModelClass = AudioViewModel::class.java

    override fun onStart() {
        super.onStart()
        viewModel.okCLick.observe(this, EventObserver { dismissDialog() })
    }

    private fun dismissDialog() {
        audioViewModel.audio.playButtonSound(requireContext())
        dismiss()
    }

}