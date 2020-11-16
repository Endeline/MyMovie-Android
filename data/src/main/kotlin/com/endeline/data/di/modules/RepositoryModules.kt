package com.endeline.data.di.modules

import com.endeline.data.service.MovieDbService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModules {

    @Provides
    fun provideMovieDbRepository() = MOVIE_DB_REPOSITORY

    companion object {
        //todo correct singleton
        val MOVIE_DB_REPOSITORY = MovieDbService()
    }
}
