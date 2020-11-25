package com.endeline.domain.di.modules

import com.endeline.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

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

    @Provides
    fun provideGetSimilarMovieUseCase() = GetSimilarMovieUseCase()

    @Provides
    fun provideGetRecommendationMovieUseCase() = GetRecommendedMovieUseCase()

    @Provides
    fun provideGetVideoLinkUseCase() = GetVideoLinksUseCase()

    @Provides
    fun provideSearchAllUseCase() = SearchAllUseCase()

    @Provides
    fun provideInitializeUserServiceUseCase() = InitializeUserServiceUseCase()

    @Provides
    fun providesGetUserIsLoggedInUseCase() = GetUserIsLoggedInUseCase()

    @Provides
    fun provideGetUserByLoginUserCase() = GetUserByLoginUseCase()

    @Provides
    fun provideCheckIsUserInAppUseCase() = CheckIsUserInAppUseCase()

    @Provides
    fun provideCheckExistLoginUseCase() = CheckExistLoginUseCase()

    @Provides
    fun provideRegisterUseCase() = RegisterUseCase()

    @Provides
    fun provideGetTheAirUseUseCase() = GetTheAirUseUseCase()

    @Provides
    fun provideGetAiringTodayUseCase() = GetAiringTodayUseCase()

    @Provides
    fun provideGetProductImagesUseCase() = GetProductImagesUseCase()

    @Provides
    fun provideGetProductReviewUseCase() = GetProductReviewUseCase()

    @Provides
    fun provideGetProductCreditsUseCase() = GetProductCreditsUseCase()
}
