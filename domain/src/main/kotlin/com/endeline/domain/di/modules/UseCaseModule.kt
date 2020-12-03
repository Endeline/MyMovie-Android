package com.endeline.domain.di.modules

import com.endeline.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun providesLoadAllDataUseCase() = LoadAllDataUseCase()

    @Provides
    fun provideGetMovieDetailsUseCase() = GetMovieDetailsUseCase()

    @Provides
    fun provideGetProductAdditionalInformationUseCase() = GetProductAdditionalInformationUseCase()

    @Provides
    fun provideGetProductVideoLinkUseCase() = GetProductVideoLinksUseCase()

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
    fun provideGetProductImagesUseCase() = GetProductImagesUseCase()

    @Provides
    fun provideGetProductReviewUseCase() = GetProductReviewUseCase()

    @Provides
    fun provideGetProductCreditsUseCase() = GetProductCreditsUseCase()

    @Provides
    fun provideGetPersonDetailUseCase() = GetPersonDetailsUseCase()

    @Provides
    fun provideGetProductsWithTypes() = GetProductsWithTypes()
}
