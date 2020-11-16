package com.endeline.domain.usecase

import com.endeline.data.service.UserService
import com.endeline.domain.di.components.DaggerDomainComponents
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.uimodels.UserUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithTwoParams
import javax.inject.Inject

class GetUserByLoginUseCase : ObservableUseCaseWithTwoParams<String, UserUiModel> {

    @Inject
    lateinit var userService: UserService

    init {
        DaggerDomainComponents.create().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(login: String) =
        userService.getUserByLogin(login)
            .toObservable()
            .map { it.toUiModel() }
}