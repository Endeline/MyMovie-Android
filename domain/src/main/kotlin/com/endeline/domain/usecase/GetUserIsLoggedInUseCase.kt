package com.endeline.domain.usecase

import com.endeline.data.service.UserService
import com.endeline.domain.di.components.DaggerServiceComponent
import com.endeline.domain.usecase.types.SingleUseCase
import javax.inject.Inject

class GetUserIsLoggedInUseCase : SingleUseCase<Boolean> {

    @Inject
    lateinit var userService: UserService

    init {
        DaggerServiceComponent.builder().build().inject(this)
    }

    override fun invoke() = userService.getCurrentUser()
}