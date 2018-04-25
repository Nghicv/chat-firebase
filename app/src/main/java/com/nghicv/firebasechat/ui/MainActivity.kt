package com.nghicv.firebasechat.ui

import android.arch.lifecycle.ViewModelProviders
import com.nghicv.baseproject.BaseActivity
import com.nghicv.firebasechat.R
import com.nghicv.firebasechat.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel: MainViewModel
        get() = ViewModelProviders.of(this).get(MainViewModel::class.java)
    override val layoutId: Int
        get() = R.layout.activity_main
}