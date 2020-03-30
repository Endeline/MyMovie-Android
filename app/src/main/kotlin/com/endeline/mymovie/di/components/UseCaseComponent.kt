package com.endeline.mymovie.di.components

import com.endeline.domain.di.modules.UseCaseModules
import com.endeline.mymovie.viewmodels.*
import dagger.Component

@Component(modules = [UseCaseModules::class])
interface UseCaseComponent {

    fun inject(app: SplashViewModel)
    fun inject(app: NowPlayingViewModel)
    fun inject(app: PopularViewModel)
    fun inject(app: TopRatedViewModel)
    fun inject(app: UpcomingViewModel)
    fun inject(app: DetailsViewModel)

}
