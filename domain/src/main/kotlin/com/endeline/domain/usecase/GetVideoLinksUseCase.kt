package com.endeline.domain.usecase

import com.endeline.data.repositories.MovieDbRepository
import com.endeline.domain.di.components.DaggerRepositoryComponent
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.types.ObservableUseCaseWithTwoParams
import com.endeline.domain.uimodels.VideoLinkCollectionUiModel
import io.reactivex.Observable
import javax.inject.Inject

class GetVideoLinksUseCase: ObservableUseCaseWithTwoParams<Int, VideoLinkCollectionUiModel> {

    @Inject
    protected lateinit var repository: MovieDbRepository

    init {
        DaggerRepositoryComponent.builder().build().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(id: Int): Observable<VideoLinkCollectionUiModel> =
        repository.getVideoLink(id)
            .map { it.toUiModel() }

}
