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
    fun providesMainActivityViewModel() = ViewModelFactory.MainActivityViewModelFactory()

    @Provides
    fun providesUserViewModel() = ViewModelFactory.UserViewModelFactory()

    @Provides
    fun providesRegisterViewModel() = ViewModelFactory.RegisterViewModelFactory()

    @Provides
    fun providePersonViewModel() = ViewModelFactory.PersonViewModelFactory()
}
