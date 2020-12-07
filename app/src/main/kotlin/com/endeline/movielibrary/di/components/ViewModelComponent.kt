package com.endeline.movielibrary.di.components

import com.endeline.domain.di.modules.UseCaseModule
import com.endeline.movielibrary.di.ViewModelFactory
import dagger.Component

@Component(modules = [UseCaseModule::class])
interface ViewModelComponent {
    fun inject(app: ViewModelFactory.SplashScreenViewModelFactory)
    fun inject(app: ViewModelFactory.DetailsViewModelFactory)
    fun inject(app: ViewModelFactory.SearchViewModelFactory)
    fun inject(app: ViewModelFactory.MainActivityViewModelFactory)
    fun inject(app: ViewModelFactory.UserViewModelFactory)
    fun inject(app: ViewModelFactory.RegisterViewModelFactory)
    fun inject(app: ViewModelFactory.SectionViewModelFactory)
    fun inject(app: ViewModelFactory.PersonViewModelFactory)
}
