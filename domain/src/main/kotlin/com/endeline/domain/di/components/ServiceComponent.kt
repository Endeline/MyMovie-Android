package com.endeline.domain.di.components

import com.endeline.data.di.modules.ServiceModules
import com.endeline.domain.usecase.*
import dagger.Component

@Component(modules = [ServiceModules::class])
interface ServiceComponent {
    fun inject(useCase: InitializeUserServiceUseCase)
    fun inject(useCase: GetUserByLoginUseCase)
    fun inject(useCase: GetUserIsLoggedInUseCase)
    fun inject(useCase: CheckIsUserInAppUseCase)
    fun inject(useCase: CheckExistLoginUseCase)
    fun inject(useCase: RegisterUseCase)
}