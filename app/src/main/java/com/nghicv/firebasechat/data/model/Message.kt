package com.nghicv.firebasechat.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "message")
class Message(
        @PrimaryKey var id: String,
        @ColumnInfo(name = "content") var content: String) {

}