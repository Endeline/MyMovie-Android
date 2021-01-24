package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.UserUiModel
import com.endeline.movielibrary.common.Constants.DEFAULT_VALUE
import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.data.entities.UserEntity

//todo fix
fun UserEntity.toUiModel() = UserUiModel(
    id = this.id ?: DEFAULT_VALUE,
    login = this.login ?: EMPTY_TEXT,
    password = this.password ?: EMPTY_TEXT
)
