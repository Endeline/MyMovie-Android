package com.endeline.movielibrary.di.modules

import androidx.lifecycle.ViewModelProvider
import com.endeline.movielibrary.di.factory.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}