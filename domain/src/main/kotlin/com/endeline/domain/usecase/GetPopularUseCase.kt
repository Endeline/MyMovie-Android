package com.endeline.domain.usecase

import com.endeline.data.services.MovieDbService
import com.endeline.domain.di.components.DaggerRepositoryComponent
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.usecase.types.ObservableUseCase
import com.endeline.domain.uimodels.MovieCollectionUiModel
import io.reactivex.Observable
import javax.inject.Inject

class GetPopularUseCase : ObservableUseCase<MovieCollectionUiModel> {

    @Inject
    lateinit var repository: MovieDbService

    init {
        DaggerRepositoryComponent.builder().build().inject(this)
    }

    override fun invoke(): Observable<MovieCollectionUiModel> =
        repository.popular.map { it.toUiModel() }
}
