package com.endeline.domain.di.components

import com.endeline.data.di.modules.RepositoryModules
import com.endeline.data.di.modules.ServiceModules
import com.endeline.domain.usecase.*
import dagger.Component

@Component(modules = [ServiceModules::class, RepositoryModules::class])
interface DomainComponents {
    fun inject(useCase: GetNowPlayingUseCase)
    fun inject(useCase: GetPopularUseCase)
    fun inject(useCase: GetTopRatedUseCase)
    fun inject(useCase: GetUpcomingUseCase)
    fun inject(useCase: LoadAllDataUseCase)
    fun inject(useCase: GetMovieDetailsUseCase)
    fun inject(useCase: GetSimilarMovieUseCase)
    fun inject(useCase: GetRecommendedMovieUseCase)
    fun inject(useCase: GetVideoLinksUseCase)
    fun inject(useCase: SearchAllUseCase)
    fun inject(useCase: GetTheAirUseUseCase)
    fun inject(useCase: GetAiringTodayUseCase)
    fun inject(useCase: InitializeUserServiceUseCase)
    fun inject(useCase: GetUserByLoginUseCase)
    fun inject(useCase: GetUserIsLoggedInUseCase)
    fun inject(useCase: CheckIsUserInAppUseCase)
    fun inject(useCase: CheckExistLoginUseCase)
    fun inject(useCase: RegisterUseCase)
    fun inject(useCase: GetProductImagesUseCase)
    fun inject(useCase: GetProductReviewUseCase)
    fun inject(useCase: GetProductCreditsUseCase)
    fun inject(useCase: GetPersonDetailsUseCase)
}
