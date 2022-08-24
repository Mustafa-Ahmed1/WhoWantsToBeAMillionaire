package com.frenchfriesfamily.whowantstobeamillionaire.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

// TODO: create base fragment with view models & data binding to reduce duplication

abstract class BaseFragment<VDB : ViewDataBinding, VM : ViewModel> : Fragment() {

    private lateinit var _binding: ViewBinding
    protected val binding: VDB
        get() = _binding as VDB


    abstract val inflater: (LayoutInflater, ViewGroup?, Boolean) -> VDB
    abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflater(layoutInflater, container, false)
        return binding.root
    }


}