package com.endeline.domain.usecase

import com.endeline.data.service.UserService
import com.endeline.domain.di.components.DaggerServiceComponent
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.uimodels.UserUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithTwoParams
import io.reactivex.Observable
import javax.inject.Inject

class GetUserByLoginUseCase: ObservableUseCaseWithTwoParams<String, UserUiModel> {

    @Inject
    protected lateinit var userService: UserService

    init {
        DaggerServiceComponent.builder().build().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(login: String): Observable<UserUiModel> {
        return userService.getUserByLogin(login)
            .toObservable()
            .map { it.toUiModel() }
    }

}