package com.endeline.domain.usecase

import com.endeline.data.repositories.MovieDbRepository
import com.endeline.domain.di.components.DaggerRepositoryComponent
import com.endeline.domain.extensions.toEntity
import com.endeline.domain.types.CompletableUseCaseWithParam
import com.endeline.domain.uimodels.MovieDetailsUiModel
import io.reactivex.Completable
import javax.inject.Inject


class AddCacheMovieDetailsUseCase : CompletableUseCaseWithParam<MovieDetailsUiModel> {

    @Inject
    protected lateinit var repository: MovieDbRepository

    init {
        DaggerRepositoryComponent.builder().build().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(uiModel: MovieDetailsUiModel): Completable {
        repository.addCachedMovieDetails(uiModel.toEntity())

        return Completable.complete()
    }

}
