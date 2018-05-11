package com.nghicv.firebasechat.data.repository

import com.nghicv.firebasechat.data.model.Message
import com.nghicv.firebasechat.data.model.User


interface AppDataSource {

    interface OnDataChangedListener<T> {
        fun onSuccess(data: T)

        fun onError(message: String)
    }

    fun saveMessage(message: Message)

    fun addUser(user: User, callback: OnDataChangedListener<User>?)
}