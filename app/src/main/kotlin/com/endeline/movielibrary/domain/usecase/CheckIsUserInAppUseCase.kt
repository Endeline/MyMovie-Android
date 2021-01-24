package com.endeline.movielibrary.domain.usecase

import com.endeline.movielibrary.data.service.UserService
import com.endeline.movielibrary.domain.usecase.types.ObservableUseCaseWithThreeParams
import javax.inject.Inject

class CheckIsUserInAppUseCase @Inject constructor(private val userService: UserService) :
    ObservableUseCaseWithThreeParams<String, String, Boolean> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(login: String, password: String) =
        userService.checkUserIsInApp(login, password)
}
