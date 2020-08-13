package com.endeline.domain.usecase

import com.endeline.data.service.UserService
import com.endeline.domain.di.components.DaggerServiceComponent
import com.endeline.domain.usecase.types.ObservableUseCaseWithThreeParams
import io.reactivex.Observable
import javax.inject.Inject

class CheckIsUserInAppUseCase : ObservableUseCaseWithThreeParams<String, String, Boolean> {

    @Inject
    protected lateinit var userService: UserService

    init {
        DaggerServiceComponent.builder().build().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(login: String, password: String): Observable<Boolean> {
//        return Observable.just(false)
        return userService.checkUserIsInApp(login, password)
    }

}