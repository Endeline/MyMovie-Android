package com.endeline.data.di.modules

import com.endeline.data.repositories.MovieDbRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModules {

    @Provides
    fun provideMovieDbRepository() = MovieDbRepository()

}
