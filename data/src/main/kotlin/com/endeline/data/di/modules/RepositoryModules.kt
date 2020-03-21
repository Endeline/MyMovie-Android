package com.endeline.data.di.modules

import com.endeline.data.dummy.DummyRepository
import com.endeline.data.repositories.MovieDbRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModules {

    @Provides
    fun provideDummyRepository() = DummyRepository()

    @Provides
    fun provideMovieDbRepository() = MovieDbRepository()

}
