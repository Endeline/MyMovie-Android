package com.endeline.data.dao

import androidx.room.*
import com.endeline.data.entities.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: UserEntity): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity): Long

    @Query("SELECT * FROM user")
    fun getAll(): Maybe<List<UserEntity>>

    @Query("SELECT * FROM user WHERE user.id = :id")
    fun getById(id: Int): Maybe<UserEntity>

    @Query("SELECT * FROM user WHERE user.login = :login")
    fun getByLogin(login: String): Maybe<UserEntity>

    @Delete
    fun delete(user: UserEntity): Completable

}