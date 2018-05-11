package com.nghicv.firebasechat.data.source.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.nghicv.firebasechat.data.model.Message
import com.nghicv.firebasechat.data.model.User
import com.nghicv.firebasechat.data.repository.AppDataSource

class AppRemoteDataSource : AppDataSource {

    private val database = FirebaseFirestore.getInstance()

    override fun addUser(user: User, callback: AppDataSource.OnDataChangedListener<User>?) {
        database.collection("users").add(user)
                .addOnSuccessListener { callback?.onSuccess(user) }
                .addOnFailureListener { callback?.onError(it.message!!) }
    }

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