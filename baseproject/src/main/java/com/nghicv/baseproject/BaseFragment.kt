package com.nghicv.baseproject

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment<VM: BaseViewModel, V: ViewDataBinding> : Fragment() {

    protected abstract val layoutId: Int @LayoutRes get
    protected abstract val viewModel: VM
    protected lateinit var binding: V

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        initView(savedInstanceState)
        return binding.root
    }

    abstract fun initView(savedInstanceState: Bundle?)
}
