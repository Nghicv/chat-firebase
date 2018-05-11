package com.nghicv.firebasechat.ui.login

import android.app.Application
import com.facebook.AccessToken
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.nghicv.baseproject.BaseViewModel
import com.nghicv.firebasechat.data.model.Empty
import com.nghicv.firebasechat.data.model.User
import com.nghicv.firebasechat.data.repository.AppRepository
import com.nghicv.firebasechat.util.toUser
import io.reactivex.subjects.PublishSubject

class LoginViewModel(private val appRepository: AppRepository, application: Application) :
        BaseViewModel(application) {

    val loginSuccessSubject: PublishSubject<User> = PublishSubject.create()
    val loginErrorSubject: PublishSubject<Empty> = PublishSubject.create()
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun handleFacebookAccessToken(accessToken: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(accessToken.token)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                firebaseAuth.currentUser?.let { user -> loginSuccessSubject.onNext(user.toUser()) }
            } else {
                loginErrorSubject.onNext(Empty())
            }
        }
    }

    fun checkLogin(): Boolean {
        return firebaseAuth.currentUser != null
    }

    fun getUser(): User? {
        return firebaseAuth.currentUser?.toUser()
    }
}