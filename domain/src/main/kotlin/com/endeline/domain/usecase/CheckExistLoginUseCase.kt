package com.endeline.domain.usecase

import com.endeline.data.entities.UserEntity
import com.endeline.data.service.UserService
import com.endeline.domain.di.components.DaggerServiceComponent
import com.endeline.domain.usecase.types.ObservableUseCaseWithTwoParams
import io.reactivex.Observable
import javax.inject.Inject

class CheckExistLoginUseCase : ObservableUseCaseWithTwoParams<String, Boolean> {

    @Inject
    lateinit var userService: UserService

    init {
        DaggerServiceComponent.builder().build().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(login: String): Observable<Boolean> {
        val user: UserEntity? = userService.getUserByLogin(login).blockingGet()

        return Observable.just(user?.login == login)
    }
}
