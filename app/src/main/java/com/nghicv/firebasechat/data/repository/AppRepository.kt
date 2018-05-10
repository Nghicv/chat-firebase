package com.nghicv.firebasechat.data.repository

import com.nghicv.firebasechat.data.model.Message


class AppRepository private constructor(
        private val appLocalDataSource: AppDataSource,
        private val appRemoteDataSource: AppDataSource) : AppDataSource {

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