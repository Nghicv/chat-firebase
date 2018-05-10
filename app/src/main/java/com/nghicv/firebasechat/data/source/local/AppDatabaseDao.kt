package com.nghicv.firebasechat.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import com.nghicv.firebasechat.data.model.Message

@Dao
interface AppDatabaseDao {

    @Insert
    fun insertMessage(message: Message)
}