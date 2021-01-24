package com.endeline.movielibrary.data.database

import android.app.Application
import androidx.room.Room
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDatabase @Inject constructor(application: Application) {

    private val database: AppAbstractDatabase = Room.databaseBuilder(
        application,
        AppAbstractDatabase::class.java,
        DATABASE_NAME
    ).build()

    val userDatabase get() = database.userDao()

    companion object {
        const val DATABASE_NAME = "endeline_database"
    }
}
