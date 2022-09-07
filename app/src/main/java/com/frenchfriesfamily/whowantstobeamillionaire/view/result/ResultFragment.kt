package com.frenchfriesfamily.whowantstobeamillionaire.view.result

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.frenchfriesfamily.whowantstobeamillionaire.R
import com.frenchfriesfamily.whowantstobeamillionaire.databinding.FragmentResultBinding
import com.frenchfriesfamily.whowantstobeamillionaire.utils.EventObserver
import com.frenchfriesfamily.whowantstobeamillionaire.view.AudioViewModel
import com.frenchfriesfamily.whowantstobeamillionaire.view.base.BaseFragment

class ResultFragment :
    BaseFragment<FragmentResultBinding, ResultsViewModel, AudioViewModel>(R.layout.fragment_result) {

    override val viewModelClass = ResultsViewModel::class.java
    override val audioViewModelClass = AudioViewModel::class.java

    private val args: ResultFragmentArgs by navArgs()

    override fun setUp() {
        binding.apply {
            textLevel.text = args.stageDetails?.level.toString()
            textMoney.text = args.stageDetails?.reward
        }

        viewModel.homeCLick.observe(this, EventObserver { navToHome() })
    }

    private fun navToHome() {
        audioViewModel.audio.playButtonSound(requireContext())
        findNavController().popBackStack(R.id.homeFragment, false)
    }

}