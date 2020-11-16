package com.endeline.mymovie.di.components

import com.endeline.domain.di.modules.UseCaseModule
import com.endeline.mymovie.di.ViewModelFactory
import dagger.Component

@Component(modules = [UseCaseModule::class])
interface ViewModelComponent {
    fun inject(app: ViewModelFactory.SplashScreenViewModelFactory)
    fun inject(app: ViewModelFactory.DetailsViewModelFactory)
    fun inject(app: ViewModelFactory.SearchViewModelFactory)
    fun inject(app: ViewModelFactory.MainActivityViewModel)
    fun inject(app: ViewModelFactory.UserViewModel)
    fun inject(app: ViewModelFactory.RegisterViewModel)
    fun inject(app: ViewModelFactory.SectionViewModelFactory)
}
