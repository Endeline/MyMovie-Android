package com.endeline.movielibrary.data.service

import android.content.Context
import com.endeline.movielibrary.data.database.AppDatabase
import com.endeline.movielibrary.data.entities.UserEntity
import com.endeline.movielibrary.data.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class UserService @Inject constructor(private val userRepository: UserRepository, appDatabase: AppDatabase) {
    private val userDatabase = appDatabase.userDatabase

    //todo remove this
    fun initDatabase(context: Context): Completable {
        return Completable.complete()
    }

    fun saveUser(userEntity: UserEntity): Observable<UserEntity> {
        userEntity.id = userDatabase.insert(userEntity)
        userRepository.saveCurrentUser(userEntity)

        return Observable.just(userEntity)
    }

    fun getCurrentUser() = Single.just(userRepository.isAnyUser())

    fun getAllUsers() = userDatabase.getAll()

    fun getUserById(id: Long) = userDatabase.getById(id)

    fun getUserByLogin(login: String) = userDatabase.getByLogin(login)

    fun checkUserIsInApp(login: String, password: String): Observable<Boolean> {
        val userEntity = userDatabase.getByLogin(login).blockingGet()

        return Observable.just(userEntity?.login == login && userEntity.password == password)
    }
}
