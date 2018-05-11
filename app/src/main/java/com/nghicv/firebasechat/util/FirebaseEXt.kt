package com.nghicv.firebasechat.util

import com.google.firebase.auth.FirebaseUser
import com.nghicv.firebasechat.data.model.User

fun FirebaseUser.toUser(): User {
    val name = displayName
    val uuid = uid
    val url = photoUrl?.toString()
    val email = email
    return User(uuid, name!!, email!!, url, null, null)
}