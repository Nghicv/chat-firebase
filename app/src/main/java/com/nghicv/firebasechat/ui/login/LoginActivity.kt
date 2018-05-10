package com.nghicv.firebasechat.ui.login

import com.nghicv.baseproject.BaseActivity
import com.nghicv.firebasechat.R
import com.nghicv.firebasechat.databinding.ActivityLoginBinding
import com.nghicv.firebasechat.util.obtainViewModel

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override val viewModel: LoginViewModel
        get() = obtainViewModel(LoginViewModel::class.java)

    override val layoutId: Int
        get() = R.layout.activity_login
}