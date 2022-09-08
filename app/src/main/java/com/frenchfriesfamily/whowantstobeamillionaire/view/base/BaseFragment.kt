package com.frenchfriesfamily.whowantstobeamillionaire.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.frenchfriesfamily.whowantstobeamillionaire.BR

abstract class BaseFragment<VDB : ViewDataBinding, VM : ViewModel, AVM : ViewModel>(private val layoutId: Int) :
    Fragment() {

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
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        _binding.apply {
            setVariable(BR.viewModel, this@BaseFragment.viewModel)
            setVariable(BR.audioViewModel, this@BaseFragment.audioViewModel)
            lifecycleOwner = this@BaseFragment
            return root
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity())[viewModelClass]
        audioViewModel = ViewModelProvider(requireActivity())[audioViewModelClass]
    }

    fun navigate(action: NavDirections) = findNavController().navigate(action)

    fun popBackStack(id: Int) = findNavController().popBackStack(id, false)

    fun popBackStack() = findNavController().popBackStack()

}