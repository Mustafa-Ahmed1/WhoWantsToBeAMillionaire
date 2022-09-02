package com.frenchfriesfamily.whowantstobeamillionaire.view.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VDB : ViewDataBinding>(private val layoutId: Int) :
    AppCompatActivity() {


    private lateinit var _binding: VDB
    protected val binding: VDB get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("HELLOWESAM", "hey wesam I'm here!")
        _binding = DataBindingUtil.setContentView(this@BaseActivity, layoutId)
    }

}