package com.nghicv.firebasechat.data.source.local

import com.nghicv.firebasechat.data.model.Message
import com.nghicv.firebasechat.data.model.User
import com.nghicv.firebasechat.data.repository.AppDataSource
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppLocalDataSource private constructor(private val appDatabaseDao: AppDatabaseDao) :
        AppDataSource {

    override fun addUser(user: User, callback: AppDataSource.OnDataChangedListener<User>?) {

    }

    override fun saveMessage(message: Message) {
        Completable.fromAction { appDatabaseDao.insertMessage(message) }.subscribeOn(Schedulers
                .newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe { }
    }

    companion object {

        private var instance: AppLocalDataSource? = null

        @JvmStatic
        fun getInstance(appDatabaseDao: AppDatabaseDao): AppLocalDataSource {
            if (instance == null) {
                synchronized(AppLocalDataSource::javaClass) {
                    instance = AppLocalDataSource(appDatabaseDao)
                }
            }

            return instance!!
        }
    }
}