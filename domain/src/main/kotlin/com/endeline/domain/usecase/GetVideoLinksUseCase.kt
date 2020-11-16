package com.endeline.domain.usecase

import com.endeline.data.service.MovieDbService
import com.endeline.domain.di.components.DaggerRepositoryComponent
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.uimodels.VideoLinkCollectionUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithTwoParams
import javax.inject.Inject

class GetVideoLinksUseCase : ObservableUseCaseWithTwoParams<Int, VideoLinkCollectionUiModel> {

    @Inject
    lateinit var repository: MovieDbService

    init {
        DaggerRepositoryComponent.builder().build().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(id: Int) =
        repository.getVideoLink(id)
            .map { it.toUiModel() }
}
