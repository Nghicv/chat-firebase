package com.nghicv.firebasechat.util

import android.content.Context
import android.support.v7.app.AlertDialog

object DialogUtil {

    fun showError(context: Context, message: String) {
        AlertDialog.Builder(context).apply {
            setMessage(message)
            setPositiveButton("Ok", null)
        }.create().show()
    }
}