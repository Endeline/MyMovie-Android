package com.endeline.mymovie.di.components

import com.endeline.domain.di.modules.UseCaseModules
import com.endeline.mymovie.di.ViewModelFactory
import dagger.Component

@Component(modules = [UseCaseModules::class])
interface UseCaseComponent {

    fun inject(app: ViewModelFactory.SplashScreenViewModelFactory)
    fun inject(app: ViewModelFactory.DetailsViewModelFactory)
    fun inject(app: ViewModelFactory.SearchViewModelFactory)
    fun inject(app: ViewModelFactory.MainActivityViewModel)
    fun inject(app: ViewModelFactory.UserViewModel)
    fun inject(app: ViewModelFactory.RegisterViewModel)
    fun inject(app: ViewModelFactory.SectionViewModelFactory)
}
