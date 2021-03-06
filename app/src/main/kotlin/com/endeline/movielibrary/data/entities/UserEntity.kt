package com.endeline.movielibrary.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    @ColumnInfo(name = "login")
    var login: String? = null,

    @ColumnInfo(name = "password")
    var password: String? = null
)