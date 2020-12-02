package com.endeline.data.di.modules

import com.endeline.data.service.ProductService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModules {

    @Provides
    fun provideMovieDbRepository() = MOVIE_DB_REPOSITORY

    companion object {
        //todo correct singleton
        private val MOVIE_DB_REPOSITORY = ProductService()
    }
}
