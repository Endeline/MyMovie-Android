package com.endeline.movielibrary.domain.usecase

import com.endeline.movielibrary.data.entities.UserEntity
import com.endeline.movielibrary.data.service.UserService
import com.endeline.movielibrary.domain.extensions.toUiModel
import com.endeline.movielibrary.domain.uimodels.UserUiModel
import com.endeline.movielibrary.domain.usecase.types.ObservableUseCaseWithThreeParams
import io.reactivex.Observable
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val userService: UserService) :
    ObservableUseCaseWithThreeParams<String, String, UserUiModel> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(login: String, password: String): Observable<UserUiModel> {
        val userEntity = UserEntity(login = login, password = password)

        return userService.saveUser(userEntity)
            .map { it.toUiModel() }
    }
}
