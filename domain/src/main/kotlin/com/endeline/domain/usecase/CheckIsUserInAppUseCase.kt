package com.endeline.domain.usecase

import com.endeline.data.service.UserService
import com.endeline.domain.di.components.DaggerServiceComponent
import com.endeline.domain.usecase.types.ObservableUseCaseWithThreeParams
import javax.inject.Inject

class CheckIsUserInAppUseCase : ObservableUseCaseWithThreeParams<String, String, Boolean> {

    @Inject
    lateinit var userService: UserService

    init {
        DaggerServiceComponent.builder().build().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(login: String, password: String) =
        userService.checkUserIsInApp(login, password)
}
