package com.endeline.data.di.modules

import com.endeline.data.services.MovieDbService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModules {

    companion object {
        //todo correct singleton
        val MOVIE_DB_REPOSITORY = MovieDbService()
    }

    @Provides
    fun provideMovieDbRepository() = MOVIE_DB_REPOSITORY

}
