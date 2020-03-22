package com.endeline.mymovie.di.modules

import com.endeline.mymovie.viewmodels.*
import dagger.Module
import dagger.Provides

@Module
class ViewModelModules {

    @Provides
    fun provideSplashScreenViewModel() = SplashViewModel()

    @Provides
    fun providesNowPlayingViewModel() = NowPlayingViewModel()

    @Provides
    fun providesLatestViewModel() = LatestViewModel()

    @Provides
    fun providesPopularViewModel() = PopularViewModel()

    @Provides
    fun provideSearchViewModel() = SearchViewModel()

    @Provides
    fun provideTopRatedViewModel() = TopRatedViewModel()

    @Provides
    fun provideUpcomingViewModel() = UpcomingViewModel()

}
