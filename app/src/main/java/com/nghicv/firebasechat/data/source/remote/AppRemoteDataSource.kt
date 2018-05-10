package com.nghicv.firebasechat.data.source.remote

import com.nghicv.firebasechat.data.model.Message
import com.nghicv.firebasechat.data.repository.AppDataSource

class AppRemoteDataSource : AppDataSource {
    override fun saveMessage(message: Message) {

    }

    companion object {

        private var instance: AppRemoteDataSource? = null

        fun getInstance(): AppRemoteDataSource {
            if (instance == null) {
                synchronized(AppRemoteDataSource::javaClass) {
                    instance = AppRemoteDataSource()
                }
            }

            return instance!!
        }
    }
}