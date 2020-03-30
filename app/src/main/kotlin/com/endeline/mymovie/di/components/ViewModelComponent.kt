package com.endeline.mymovie.di.components

import com.endeline.mymovie.MainActivity
import com.endeline.mymovie.SplashScreenActivity
import com.endeline.mymovie.di.modules.ViewModelModules
import com.endeline.mymovie.fragments.*
import com.endeline.mymovie.ui.adapters.MovieAdapter
import com.endeline.mymovie.fragments.NowPlayingFragment
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

}