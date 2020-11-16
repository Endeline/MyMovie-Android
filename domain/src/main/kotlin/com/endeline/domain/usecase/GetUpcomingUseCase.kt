package com.endeline.domain.usecase

import com.endeline.data.service.MovieDbService
import com.endeline.domain.ProductType
import com.endeline.domain.di.components.DaggerDomainComponents
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.uimodels.ProductsUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithTwoParams
import io.reactivex.Observable
import javax.inject.Inject

class GetUpcomingUseCase : ObservableUseCaseWithTwoParams<ProductType, ProductsUiModel> {

    @Inject
    lateinit var repository: MovieDbService

    init {
        DaggerDomainComponents.create().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(productType: ProductType): Observable<ProductsUiModel> =
        repository.getUpcoming(productType.name).map { it.toUiModel(productType) }
}
