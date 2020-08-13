package com.endeline.domain.usecase

import android.content.Context
import com.endeline.data.service.UserService
import com.endeline.domain.di.components.DaggerServiceComponent
import com.endeline.domain.usecase.types.CompletableUseCaseWithParam

import javax.inject.Inject

class InitializeUserServiceUseCase : CompletableUseCaseWithParam<Context> {

    @Inject
    protected lateinit var userService: UserService

    init {
        DaggerServiceComponent.builder().build().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(context: Context) =
        userService.initDatabase(context)
}