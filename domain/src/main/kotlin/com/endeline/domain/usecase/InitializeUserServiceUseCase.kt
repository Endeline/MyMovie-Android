package com.endeline.domain.usecase

import android.content.Context
import com.endeline.data.service.UserService
import com.endeline.domain.di.components.DaggerDomainComponents
import com.endeline.domain.usecase.types.CompletableUseCaseWithParam

import javax.inject.Inject

class InitializeUserServiceUseCase : CompletableUseCaseWithParam<Context> {

    @Inject
    lateinit var userService: UserService

    init {
        DaggerDomainComponents.create().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(context: Context) = userService.initDatabase(context)
}