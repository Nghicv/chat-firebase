package com.nghicv.firebasechat.inject

import android.content.Context
import com.nghicv.firebasechat.data.repository.AppRepository
import com.nghicv.firebasechat.data.source.local.AppDatabase
import com.nghicv.firebasechat.data.source.local.AppLocalDataSource
import com.nghicv.firebasechat.data.source.remote.AppRemoteDataSource

object Injection {

    fun provideAppRepository(context: Context): AppRepository {
        val database = AppDatabase.getInstance(context)
        val localDataSource = AppLocalDataSource.getInstance(database.appDatabaseDao())
        val remoteDataSource = AppRemoteDataSource.getInstance()
        return AppRepository.getInstance(localDataSource, remoteDataSource)
    }
}