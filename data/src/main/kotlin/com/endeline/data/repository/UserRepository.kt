package com.endeline.data.repository

import android.content.Context
import com.endeline.data.entities.UserEntity

class UserRepository(context: Context) {

    private val sharedPreference  = context.getSharedPreferences(
        CURRENT_USER_SHARED_PREFERENCES,
        Context.MODE_PRIVATE
    )

    fun saveCurrentUser(user: UserEntity) = with(sharedPreference.edit()) {
        putLong(ID_KEY, user.id ?: DEFAULT_ID)
        putString(LOGIN_KEY, user.login)
        putString(PASSWORD_KEY, user.password)
        commit()
    }

    fun getCurrentUser() = with(sharedPreference) {
        return@with UserEntity(
            id = getLong(ID_KEY, DEFAULT_ID),
            login = getString(LOGIN_KEY, ""),
            password = getString(PASSWORD_KEY, "")
        )
    }

    fun isAnyUser() = sharedPreference.getLong(ID_KEY, DEFAULT_ID) != DEFAULT_ID

    fun clear() = with(sharedPreference.edit()) {
        putLong(ID_KEY, DEFAULT_ID)
        putString(LOGIN_KEY, "")
        putString(PASSWORD_KEY, "")
    }

    companion object {
        private const val CURRENT_USER_SHARED_PREFERENCES = "CURRENT_USER"
        private const val ID_KEY = "id"
        private const val DEFAULT_ID = -1L
        private const val LOGIN_KEY = "login"
        private const val PASSWORD_KEY = "password"
    }
}