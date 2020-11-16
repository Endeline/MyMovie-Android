package com.endeline.domain.usecase

import com.endeline.data.service.MovieDbService
import com.endeline.domain.ProductType
import com.endeline.domain.di.components.DaggerDomainComponents
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithTwoParams
import com.endeline.domain.uimodels.ProductsUiModel
import io.reactivex.Observable
import javax.inject.Inject

class GetSimilarMovieUseCase : ObservableUseCaseWithTwoParams<Int, ProductsUiModel> {

    @Inject
    lateinit var repository: MovieDbService

    init {
        DaggerDomainComponents.builder().build().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(id: Int): Observable<ProductsUiModel> =
        repository.getSimilarMovies(id)
            .map { it.toUiModel(ProductType.other) }
}
