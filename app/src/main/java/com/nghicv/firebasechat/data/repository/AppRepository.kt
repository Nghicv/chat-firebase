package com.nghicv.firebasechat.data.repository

import com.nghicv.firebasechat.data.model.Message
import com.nghicv.firebasechat.data.model.User


class AppRepository private constructor(
        private val appLocalDataSource: AppDataSource,
        private val appRemoteDataSource: AppDataSource) : AppDataSource {

    override fun addUser(user: User, callback: AppDataSource.OnDataChangedListener<User>?) {
        appRemoteDataSource.addUser(user, callback)
    }

    override fun saveMessage(message: Message) {
        appLocalDataSource.saveMessage(message)
    }

    companion object {

        private var instance: AppRepository? = null

        @JvmStatic
        fun getInstance(appLocalDataSource: AppDataSource, appRemoteDataSource:
        AppDataSource) = instance ?: synchronized(AppRepository::class.java) {
            instance ?: AppRepository(appLocalDataSource, appRemoteDataSource).apply {
                instance = this
            }
        }

        @JvmStatic
        fun destroyInstance() {
            instance = null
        }
    }

}