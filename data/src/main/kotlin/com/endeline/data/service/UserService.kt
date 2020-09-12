package com.endeline.data.service

import android.content.Context
import androidx.room.Room
import com.endeline.data.dao.UserDao
import com.endeline.data.database.UserDatabase
import com.endeline.data.entities.UserEntity
import com.endeline.data.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class UserService {

    private lateinit var userDao: UserDao
    private lateinit var userRepository: UserRepository

    fun initDatabase(context: Context): Completable {
        //todo in final user normal database
        val database = Room.inMemoryDatabaseBuilder(context, UserDatabase::class.java)
            .allowMainThreadQueries()
            .build()
//        DATABASE = Room.databaseBuilder(context, UserDatabase::class.java, "user_database").build()

        userDao = database.userDao()
        userRepository = UserRepository(context)

        return Completable.complete()
    }

    fun saveUser(userEntity: UserEntity): Observable<UserEntity> {
        userEntity.id = userDao.insert(userEntity)
        userRepository.saveCurrentUser(userEntity)

        return Observable.just(userEntity)
    }

    fun getCurrentUser() = Single.just(userRepository.isAnyUser())

    fun getAllUsers() = userDao.getAll()

    fun getUserById(id: Long) = userDao.getById(id)

    fun getUserByLogin(login: String) = userDao.getByLogin(login)

    fun checkUserIsInApp(login: String, password: String): Observable<Boolean> {
        val userEntity = userDao.getByLogin(login).blockingGet()

        return Observable.just(userEntity?.login == login && userEntity.password == password)
    }
}
