package com.endeline.domain.usecase

import com.endeline.data.service.MovieDbService
import com.endeline.domain.di.components.DaggerDomainComponents
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithTwoParams
import com.endeline.domain.uimodels.SearchAllUiModel
import io.reactivex.Observable
import javax.inject.Inject

class SearchAllUseCase : ObservableUseCaseWithTwoParams<String, SearchAllUiModel> {

    @Inject
    lateinit var repository: MovieDbService

    init {
        DaggerDomainComponents.create().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(query: String): Observable<SearchAllUiModel> =
        repository.searchAll(query)
            .map { it.toUiModel() }
}
