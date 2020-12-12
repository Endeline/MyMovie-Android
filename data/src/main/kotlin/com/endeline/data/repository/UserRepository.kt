package com.endeline.data.repository

import android.content.Context
import com.endeline.common.Constants.DEFAULT_VALUE
import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.data.entities.UserEntity

class UserRepository(context: Context) {

    private val sharedPreference  = context.getSharedPreferences(
        CURRENT_USER_SHARED_PREFERENCES,
        Context.MODE_PRIVATE
    )

    fun saveCurrentUser(user: UserEntity) = with(sharedPreference.edit()) {
        putLong(ID_KEY, user.id ?: DEFAULT_VALUE)
        putString(LOGIN_KEY, user.login)
        putString(PASSWORD_KEY, user.password)
        commit()
    }

    fun getCurrentUser() = with(sharedPreference) {
        return@with UserEntity(
            id = getLong(ID_KEY, DEFAULT_VALUE),
            login = getString(LOGIN_KEY, EMPTY_TEXT),
            password = getString(PASSWORD_KEY, EMPTY_TEXT)
        )
    }

    fun isAnyUser() = sharedPreference.getLong(ID_KEY, DEFAULT_VALUE) != DEFAULT_VALUE

    fun clear() = with(sharedPreference.edit()) {
        putLong(ID_KEY, DEFAULT_VALUE)
        putString(LOGIN_KEY, EMPTY_TEXT)
        putString(PASSWORD_KEY, EMPTY_TEXT)
    }

    companion object {
        private const val CURRENT_USER_SHARED_PREFERENCES = "CURRENT_USER"
        private const val ID_KEY = "id"
        private const val LOGIN_KEY = "login"
        private const val PASSWORD_KEY = "password"
    }
}