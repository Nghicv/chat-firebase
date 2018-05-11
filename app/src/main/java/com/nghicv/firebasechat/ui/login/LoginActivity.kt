package com.nghicv.firebasechat.ui.login

import android.content.Intent
import android.os.Bundle
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.nghicv.baseproject.BaseActivity
import com.nghicv.firebasechat.R
import com.nghicv.firebasechat.constant.IntentKey
import com.nghicv.firebasechat.data.model.User
import com.nghicv.firebasechat.databinding.ActivityLoginBinding
import com.nghicv.firebasechat.ui.MainActivity
import com.nghicv.firebasechat.ui.login.information.InputActivity
import com.nghicv.firebasechat.util.DialogUtil
import com.nghicv.firebasechat.util.obtainViewModel

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override val viewModel: LoginViewModel
        get() = obtainViewModel(LoginViewModel::class.java)

    override val layoutId: Int
        get() = R.layout.activity_login

    private val callbackManager = CallbackManager.Factory.create()

    private val facebookCallbackResult: FacebookCallback<LoginResult> = object :
            FacebookCallback<LoginResult> {
        override fun onSuccess(result: LoginResult?) {
            result?.accessToken?.let { viewModel.handleFacebookAccessToken(it) }
        }

        override fun onCancel() {

        }

        override fun onError(error: FacebookException?) {
            DialogUtil.showError(this@LoginActivity, error?.message!!)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding.loginButton) {
            setReadPermissions("email", "public_profile")
            registerCallback(callbackManager, facebookCallbackResult)
        }
    }

    override fun onStart() {
        super.onStart()
        val user = viewModel.getUser()
        if (user != null) {
            redirectToMainScreen(user)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun subscriber() {
        bindCall(viewModel.loginSuccessSubject.subscribe { redirectToMainScreen(it) })
        bindCall(viewModel.loginErrorSubject.subscribe {
            DialogUtil.showError(this, getString(R
                    .string.error_default))
        })
    }

    private fun redirectToMainScreen(user: User) {
        startActivity(Intent(this, InputActivity::class.java).apply {
            putExtras(Bundle().apply {
                putSerializable(IntentKey.USER, user)
            })
        })
        finish()
    }
}