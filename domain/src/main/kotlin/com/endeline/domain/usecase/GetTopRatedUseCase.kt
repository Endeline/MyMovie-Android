package com.endeline.domain.usecase

import com.endeline.data.repositories.MovieDbRepository
import com.endeline.domain.di.components.DaggerRepositoryComponent
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.types.ObservableUseCase
import com.endeline.domain.uimodels.MovieCollectionUiModel
import io.reactivex.Observable
import javax.inject.Inject

class GetTopRatedUseCase : ObservableUseCase<MovieCollectionUiModel> {

    @Inject
    protected lateinit var repository: MovieDbRepository

    init {
        DaggerRepositoryComponent.builder().build().inject(this)
    }

    override fun invoke(): Observable<MovieCollectionUiModel> =
        repository.getTopRated()
            .map { it.toUiModel() }

}
