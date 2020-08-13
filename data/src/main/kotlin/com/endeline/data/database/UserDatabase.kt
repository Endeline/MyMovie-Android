package com.endeline.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.endeline.data.dao.UserDao
import com.endeline.data.entities.UserEntity

@Database(entities = arrayOf(UserEntity::class), version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}