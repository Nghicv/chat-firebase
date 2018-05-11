package com.nghicv.firebasechat.ui.login.information

import android.content.Intent
import android.os.Bundle
import android.widget.PopupMenu
import com.nghicv.baseproject.BaseActivity
import com.nghicv.firebasechat.R
import com.nghicv.firebasechat.constant.IntentKey
import com.nghicv.firebasechat.data.model.User
import com.nghicv.firebasechat.databinding.ActivityInputBinding
import com.nghicv.firebasechat.ui.MainActivity
import com.nghicv.firebasechat.util.DialogUtil
import com.nghicv.firebasechat.util.obtainViewModel

class InputActivity : BaseActivity<InputViewModel, ActivityInputBinding>() {

    override val viewModel: InputViewModel
        get() = obtainViewModel(InputViewModel::class.java)
    override val layoutId: Int
        get() = R.layout.activity_input

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
    }

    override fun subscriber() {
        bindCall(viewModel.selectGenderSubject.subscribe { showOptionMenuGender() })
        bindCall(viewModel.updateDataSuccessSubject.subscribe {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })
        bindCall(viewModel.updateDataErrorSubject.subscribe { DialogUtil.showError(this, it) })
    }

    private fun getData() {
        intent?.extras?.let {
            viewModel.user = it[IntentKey.USER] as User
        }
    }

    private fun showOptionMenuGender() {
        PopupMenu(this, binding.tvGender).apply {
            menuInflater.inflate(R.menu.gender, menu)

            setOnMenuItemClickListener {
                viewModel.gender = when (it.itemId) {
                    R.id.male -> InputViewModel.Gender.MALE

                    else -> InputViewModel.Gender.FEMALE
                }

                return@setOnMenuItemClickListener true
            }
        }.show()
    }
}