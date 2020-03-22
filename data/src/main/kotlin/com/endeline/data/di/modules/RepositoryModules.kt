package com.endeline.data.di.modules

import com.endeline.data.repositories.MovieDbRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModules {

    companion object {
        //todo correct singleton
        val MOVIE_DB_REPOSITORY = MovieDbRepository()
    }

    @Provides
    fun provideMovieDbRepository() = MOVIE_DB_REPOSITORY

}
