package com.endeline.mymovie.di.modules

import com.endeline.mymovie.ui.details.DetailsViewModel
import com.endeline.mymovie.ui.now.NowPlayingViewModel
import com.endeline.mymovie.ui.popular.PopularViewModel
import com.endeline.mymovie.ui.search.SearchViewModel
import com.endeline.mymovie.ui.splash.SplashViewModel
import com.endeline.mymovie.ui.top.TopRatedViewModel
import com.endeline.mymovie.ui.upcoming.UpcomingViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModules {

    @Provides
    fun provideSplashScreenViewModel() =
        SplashViewModel()

    @Provides
    fun providesNowPlayingViewModel() =
        NowPlayingViewModel()

    @Provides
    fun providesPopularViewModel() =
        PopularViewModel()

    @Provides
    fun provideSearchViewModel() =
        SearchViewModel()

    @Provides
    fun provideTopRatedViewModel() =
        TopRatedViewModel()

    @Provides
    fun provideUpcomingViewModel() =
        UpcomingViewModel()

    @Provides
    fun provideDetailsViewModel() =
        DetailsViewModel()

}
