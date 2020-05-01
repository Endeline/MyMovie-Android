package com.endeline.mymovie.di.components

import com.endeline.domain.di.modules.UseCaseModules
import com.endeline.mymovie.ui.details.DetailsViewModel
import com.endeline.mymovie.ui.now.NowPlayingViewModel
import com.endeline.mymovie.ui.popular.PopularViewModel
import com.endeline.mymovie.ui.search.SearchViewModel
import com.endeline.mymovie.ui.splash.SplashViewModel
import com.endeline.mymovie.ui.top.TopRatedViewModel
import com.endeline.mymovie.ui.upcoming.UpcomingViewModel
import dagger.Component

@Component(modules = [UseCaseModules::class])
interface UseCaseComponent {

    fun inject(app: SplashViewModel)
    fun inject(app: NowPlayingViewModel)
    fun inject(app: PopularViewModel)
    fun inject(app: TopRatedViewModel)
    fun inject(app: UpcomingViewModel)
    fun inject(app: DetailsViewModel)
    fun inject(app: SearchViewModel)

}
