package com.nghicv.firebasechat.factory

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nghicv.firebasechat.data.repository.AppRepository
import com.nghicv.firebasechat.inject.Injection
import com.nghicv.firebasechat.ui.login.LoginViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(
        private val application: Application,
        private val appRepository: AppRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) = with(modelClass) {
        when {
            isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(appRepository,
                    application)

            else -> throw IllegalAccessException("Class: $name not support")
        } as T
    }


    companion object {

        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(application: Application) = instance
                ?: synchronized(ViewModelFactory::class.java) {
                    instance ?: ViewModelFactory(application,
                            Injection.provideAppRepository(application.applicationContext))
                            .also { instance = it }
                }

    }
}