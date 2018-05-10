package com.nghicv.firebasechat.ui

import android.app.Application
import android.databinding.ObservableField
import com.nghicv.baseproject.BaseViewModel

class MainViewModel(application: Application) : BaseViewModel(application) {

    val name = ObservableField<String>("Nghicv")
}