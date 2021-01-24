package com.endeline.movielibrary.domain.usecase

import com.endeline.movielibrary.data.entities.UserEntity
import com.endeline.movielibrary.data.service.UserService
import com.endeline.movielibrary.domain.usecase.types.ObservableUseCaseWithTwoParams
import io.reactivex.Observable
import javax.inject.Inject

class CheckExistLoginUseCase @Inject constructor(private val userService: UserService) :
    ObservableUseCaseWithTwoParams<String, Boolean> {


    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(login: String): Observable<Boolean> {
        val user: UserEntity? = userService.getUserByLogin(login).blockingGet()

        return Observable.just(user?.login == login)
    }
}
