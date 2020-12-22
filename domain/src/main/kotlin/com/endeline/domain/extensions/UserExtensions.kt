package com.endeline.domain.extensions

import com.endeline.common.Constants.DEFAULT_VALUE
import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.data.entities.UserEntity
import com.endeline.domain.uimodels.UserUiModel

//todo fix
fun UserEntity.toUiModel() = UserUiModel(
    id = this.id ?: DEFAULT_VALUE,
    login = this.login ?: EMPTY_TEXT,
    password = this.password ?: EMPTY_TEXT
)
