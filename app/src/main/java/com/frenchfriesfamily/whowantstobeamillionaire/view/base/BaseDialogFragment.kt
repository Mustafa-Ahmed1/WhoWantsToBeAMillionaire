package com.frenchfriesfamily.whowantstobeamillionaire.view.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.frenchfriesfamily.whowantstobeamillionaire.BR

abstract class BaseDialogFragment<VDB : ViewDataBinding, VM : ViewModel, AVM : ViewModel>(private val layoutId: Int) :
    DialogFragment() {

    lateinit var viewModel: VM
    abstract val viewModelClass: Class<VM>

    lateinit var audioViewModel: AVM
    abstract val audioViewModelClass: Class<AVM>

    private lateinit var _binding: VDB
    val binding: VDB get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()

        isCancelable = false
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        _binding.apply {
            setVariable(BR.viewModel, this@BaseDialogFragment.viewModel)
            setVariable(BR.audioViewModel, this@BaseDialogFragment.audioViewModel)
            lifecycleOwner = this@BaseDialogFragment
            return root
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity())[viewModelClass]
        audioViewModel = ViewModelProvider(requireActivity())[audioViewModelClass]
    }

    fun popBackStack(id: Int) {
        findNavController().popBackStack(id, false)
    }

}