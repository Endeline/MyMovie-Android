package com.endeline.domain.di.components

import com.endeline.data.di.modules.RepositoryModules
import com.endeline.data.di.modules.ServiceModules
import com.endeline.domain.usecase.*
import dagger.Component

@Component(modules = [ServiceModules::class, RepositoryModules::class])
interface DomainComponents {
    fun inject(useCase: LoadAllDataUseCase)
    fun inject(useCase: GetMovieDetailsUseCase)
    fun inject(useCase: GetProductAdditionalInformationUseCase)
    fun inject(useCase: GetProductVideoLinksUseCase)
    fun inject(useCase: SearchAllUseCase)
    fun inject(useCase: InitializeUserServiceUseCase)
    fun inject(useCase: GetUserByLoginUseCase)
    fun inject(useCase: GetUserIsLoggedInUseCase)
    fun inject(useCase: CheckIsUserInAppUseCase)
    fun inject(useCase: CheckExistLoginUseCase)
    fun inject(useCase: RegisterUseCase)
    fun inject(useCase: GetImagesUseCase)
    fun inject(useCase: GetProductReviewUseCase)
    fun inject(useCase: GetProductCreditsUseCase)
    fun inject(useCase: GetPersonDetailsUseCase)
    fun inject(useCase: GetProductsWithTypes)
}
