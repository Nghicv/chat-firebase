package com.nghicv.firebasechat.data.repository

import com.nghicv.firebasechat.data.model.Message


interface AppDataSource {
    
    fun saveMessage(message: Message)
}