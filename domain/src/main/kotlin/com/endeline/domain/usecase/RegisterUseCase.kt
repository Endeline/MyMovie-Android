package com.endeline.domain.usecase

import com.endeline.data.entities.UserEntity
import com.endeline.data.service.UserService
import com.endeline.domain.di.components.DaggerServiceComponent
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.uimodels.UserUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithThreeParams
import io.reactivex.Observable
import javax.inject.Inject

class RegisterUseCase : ObservableUseCaseWithThreeParams<String, String, UserUiModel> {

    @Inject
    lateinit var userService: UserService

    init {
        DaggerServiceComponent.builder().build().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(login: String, password: String): Observable<UserUiModel> {
        val userEntity = UserEntity(login = login, password = password)

        return userService.saveUser(userEntity)
            .map {
                it.toUiModel()
            }
    }
}