package com.endeline.movielibrary.domain.usecase

import com.endeline.movielibrary.data.service.ProductService
import com.endeline.movielibrary.domain.extensions.toUiModel
import com.endeline.movielibrary.domain.usecase.types.ObservableUseCaseWithTwoParams
import com.endeline.movielibrary.domain.uimodels.SearchUiModel
import io.reactivex.Observable
import javax.inject.Inject

class SearchAllUseCase @Inject constructor(private val productService: ProductService) :
    ObservableUseCaseWithTwoParams<String, SearchUiModel> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(query: String): Observable<SearchUiModel> =
        productService.searchAll(query).map { it.toUiModel() }
}
