package com.nghicv.firebasechat.ui

import com.nghicv.baseproject.BaseActivity
import com.nghicv.firebasechat.R
import com.nghicv.firebasechat.databinding.ActivityMainBinding
import com.nghicv.firebasechat.util.obtainViewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel: MainViewModel
        get() = obtainViewModel(MainViewModel::class.java)
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun subscriber() {

    }
}