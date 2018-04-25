package com.nghicv.firebasechat.ui.login

import android.arch.lifecycle.ViewModelProviders
import com.nghicv.baseproject.BaseActivity
import com.nghicv.firebasechat.R
import com.nghicv.firebasechat.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override val viewModel: LoginViewModel
        get() = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    override val layoutId: Int
        get() = R.layout.activity_login
}