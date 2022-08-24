package com.frenchfriesfamily.whowantstobeamillionaire.view.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

// TODO : improve base activity if needed, or remove it
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    abstract val bindingInflater: (LayoutInflater) -> VB
    private lateinit var _binding: ViewBinding
    protected val binding: VB
        get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater(layoutInflater)
        setContentView(_binding.root)
    }


}