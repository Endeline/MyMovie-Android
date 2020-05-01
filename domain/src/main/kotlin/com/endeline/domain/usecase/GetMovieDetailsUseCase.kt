package com.endeline.domain.usecase

import com.endeline.data.services.MovieDbService
import com.endeline.domain.di.components.DaggerRepositoryComponent
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithTwoParams
import com.endeline.domain.uimodels.MovieDetailsUiModel
import io.reactivex.Observable
import javax.inject.Inject

class GetMovieDetailsUseCase : ObservableUseCaseWithTwoParams<Int, MovieDetailsUiModel> {

    @Inject
    protected lateinit var repository: MovieDbService

    init {
        DaggerRepositoryComponent.builder().build().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(id: Int): Observable<MovieDetailsUiModel> =
        repository.getMovieDetails(id)
            .map { it.toUiModel() }

}
