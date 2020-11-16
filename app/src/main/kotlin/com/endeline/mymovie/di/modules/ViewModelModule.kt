package com.endeline.mymovie.di.modules

import com.endeline.mymovie.di.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideSplashScreenViewModel() = ViewModelFactory.SplashScreenViewModelFactory()

    @Provides
    fun provideDetailsViewModel() = ViewModelFactory.DetailsViewModelFactory()

    @Provides
    fun provideSectionViewModel() = ViewModelFactory.SectionViewModelFactory()

    @Provides
    fun provideSearchViewModel() = ViewModelFactory.SearchViewModelFactory()

    @Provides
    fun providesMainActivityViewModel() = ViewModelFactory.MainActivityViewModel()

    @Provides
    fun providesUserViewModel() = ViewModelFactory.UserViewModel()

    @Provides
    fun providesRegisterViewModel() = ViewModelFactory.RegisterViewModel()
}
