package com.endeline.mymovie.di.components

import com.endeline.mymovie.di.modules.ViewModelModules
import com.endeline.mymovie.ui.adapters.MovieAdapter
import com.endeline.mymovie.ui.details.DetailsFragment
import com.endeline.mymovie.ui.main.MainActivity
import com.endeline.mymovie.ui.now.NowPlayingFragment
import com.endeline.mymovie.ui.popular.PopularFragment
import com.endeline.mymovie.ui.search.SearchFragment
import com.endeline.mymovie.ui.splash.SplashScreenActivity
import com.endeline.mymovie.ui.top.TopRatedFragment
import com.endeline.mymovie.ui.upcoming.UpcomingFragment
import dagger.Component

@Component(modules = [ViewModelModules::class])
interface ViewModelComponent {

    fun inject(app: SplashScreenActivity)
    fun inject(app: MainActivity)

    fun inject(app: MovieAdapter)

    fun inject(app: NowPlayingFragment)
    fun inject(app: PopularFragment)
    fun inject(app: TopRatedFragment)
    fun inject(app: UpcomingFragment)
    fun inject(app: DetailsFragment)
    fun inject(app: SearchFragment)

}