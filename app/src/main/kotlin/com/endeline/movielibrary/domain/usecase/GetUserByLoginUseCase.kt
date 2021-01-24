package com.endeline.movielibrary.domain.usecase

import com.endeline.movielibrary.data.service.UserService
import com.endeline.movielibrary.domain.extensions.toUiModel
import com.endeline.movielibrary.domain.uimodels.UserUiModel
import com.endeline.movielibrary.domain.usecase.types.ObservableUseCaseWithTwoParams
import javax.inject.Inject

class GetUserByLoginUseCase @Inject constructor(private val userService: UserService) :
    ObservableUseCaseWithTwoParams<String, UserUiModel> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(login: String) =
        userService.getUserByLogin(login)
            .toObservable()
            .map { it.toUiModel() }
}
