package com.endeline.domain.usecase

import com.endeline.data.service.ProductService
import com.endeline.domain.di.components.DaggerDomainComponents
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithTwoParams
import com.endeline.domain.uimodels.SearchUiModel
import io.reactivex.Observable
import javax.inject.Inject

class SearchAllUseCase : ObservableUseCaseWithTwoParams<String, SearchUiModel> {

    @Inject
    lateinit var productService: ProductService

    init {
        DaggerDomainComponents.create().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(query: String): Observable<SearchUiModel> =
        productService.searchAll(query).map { it.toUiModel() }
}
