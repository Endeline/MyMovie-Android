package com.endeline.domain.usecase

import com.endeline.data.repositories.MovieDbRepository
import com.endeline.domain.di.components.DaggerRepositoryComponent
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.types.ObservableUseCaseWithTwoParams
import com.endeline.domain.uimodels.MovieCollectionUiModel
import io.reactivex.Observable
import javax.inject.Inject

class GetRecommendedMovieUseCase : ObservableUseCaseWithTwoParams<Int, MovieCollectionUiModel> {

    @Inject
    protected lateinit var repository: MovieDbRepository

    init {
        DaggerRepositoryComponent.builder().build().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(id: Int): Observable<MovieCollectionUiModel> =
        repository.getRecommendedMovies(id)
            .map { it.toUiModel() }

}
