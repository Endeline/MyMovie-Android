package com.endeline.movielibrary.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.endeline.movielibrary.data.dao.UserDao
import com.endeline.movielibrary.data.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppAbstractDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
