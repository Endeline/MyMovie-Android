package com.endeline.domain.usecase

import com.endeline.data.repositories.MovieDbRepository
import com.endeline.domain.di.components.DaggerUseCaseComponent
import com.endeline.domain.types.ObservableUseCase
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.uimodels.MovieLatestUiModel
import io.reactivex.Observable
import javax.inject.Inject

class GetLatestUseCase :
    ObservableUseCase<MovieLatestUiModel> {

    @Inject
    protected lateinit var repository: MovieDbRepository

    init {
        DaggerUseCaseComponent.builder().build().inject(this)
    }

    override fun invoke(): Observable<MovieLatestUiModel> =
        repository.getLatest()
            .map { it.toUiModel() }
}
