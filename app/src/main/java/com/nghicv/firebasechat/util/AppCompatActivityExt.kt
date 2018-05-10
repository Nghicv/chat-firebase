package com.nghicv.firebasechat.util

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.nghicv.baseproject.BaseViewModel
import com.nghicv.firebasechat.factory.ViewModelFactory


fun <T : BaseViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)