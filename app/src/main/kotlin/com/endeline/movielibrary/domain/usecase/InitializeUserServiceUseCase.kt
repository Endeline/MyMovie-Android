package com.endeline.movielibrary.domain.usecase

import android.content.Context
import com.endeline.movielibrary.data.service.UserService
import com.endeline.movielibrary.domain.usecase.types.CompletableUseCaseWithParam

import javax.inject.Inject

//TODO remove this??
class InitializeUserServiceUseCase @Inject constructor(private val userService: UserService) :
    CompletableUseCaseWithParam<Context> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(context: Context) = userService.initDatabase(context)
}