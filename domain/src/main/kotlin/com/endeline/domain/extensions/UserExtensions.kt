package com.endeline.domain.extensions

import com.endeline.data.entities.UserEntity
import com.endeline.domain.uimodels.UserUiModel

//todo fix
fun UserEntity.toUiModel() = UserUiModel(
    id = this@toUiModel.id ?: 0,
    login = this@toUiModel.login ?: "",
    password = this@toUiModel.password ?: ""
)
