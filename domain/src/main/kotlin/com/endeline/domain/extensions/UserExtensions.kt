package com.endeline.domain.extensions

import com.endeline.data.entities.UserEntity
import com.endeline.domain.uimodels.UserUiModel

fun UserEntity.toUiModel() =
    UserUiModel().apply {
        id = this@toUiModel.id
        login = this@toUiModel.login
        password = this@toUiModel.password
    }
