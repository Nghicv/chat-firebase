package com.nghicv.firebasechat.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
class User(
        @PrimaryKey var uuid: String,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "email") var email: String,
        @ColumnInfo(name = "url") var url: String?,
        @ColumnInfo(name = "sex") var sex: String?,
        @ColumnInfo(name = "age") var age: Int?
) : Serializable