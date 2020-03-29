package com.endeline.domain.di.modules

import com.endeline.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModules {

    @Provides
    fun provideGetLatestUseCase() = GetLatestUseCase()

    @Provides
    fun provideGetNowPlayingUseCase() = GetNowPlayingUseCase()

    @Provides
    fun provideGetPopularUseCase() = GetPopularUseCase()

    @Provides
    fun providesTopRatedUseCase() = GetTopRatedUseCase()

    @Provides
    fun providesUpcomingUseCase() = GetUpcomingUseCase()

    @Provides
    fun providesLoadAllDataUseCase() = LoadAllDataUseCase()

    @Provides
    fun provideGetMovieDetailsUseCase() = GetMovieDetailsUseCase()

}
