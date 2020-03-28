package com.endeline.domain.di.components

import com.endeline.data.di.modules.RepositoryModules
import com.endeline.domain.usecase.*
import dagger.Component

@Component(modules = [RepositoryModules::class])
interface RepositoryComponent {

    fun inject(useCase: GetLatestUseCase)
    fun inject(useCase: GetNowPlayingUseCase)
    fun inject(useCase: GetPopularUseCase)
    fun inject(useCase: GetTopRatedUseCase)
    fun inject(useCase: GetUpcomingUseCase)
    fun inject(useCase: LoadAllDataUseCase)
    fun inject(useCase: GetMovieDetailsUseCase)
    fun inject(useCase: AddCacheMovieDetailsUseCase)

}
