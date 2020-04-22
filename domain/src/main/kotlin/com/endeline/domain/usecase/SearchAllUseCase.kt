package com.endeline.domain.usecase

import com.endeline.data.repositories.MovieDbRepository
import com.endeline.domain.di.components.DaggerRepositoryComponent
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.types.ObservableUseCaseWithTwoParams
import com.endeline.domain.uimodels.SearchAllUiModel
import io.reactivex.Observable
import javax.inject.Inject

class SearchAllUseCase : ObservableUseCaseWithTwoParams<String, SearchAllUiModel> {

    @Inject
    protected lateinit var repository: MovieDbRepository

    init {
        DaggerRepositoryComponent.builder().build().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(query: String): Observable<SearchAllUiModel> =
        repository.searchAll(query)
            .map {
                it.toUiModel()
            }

}
