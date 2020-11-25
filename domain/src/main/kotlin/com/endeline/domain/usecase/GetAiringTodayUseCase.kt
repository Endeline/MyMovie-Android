package com.endeline.domain.usecase

import com.endeline.data.service.ProductService
import com.endeline.domain.ProductType
import com.endeline.domain.di.components.DaggerDomainComponents
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.uimodels.ProductsUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithTwoParams
import io.reactivex.Observable
import javax.inject.Inject

class GetAiringTodayUseCase : ObservableUseCaseWithTwoParams<ProductType, ProductsUiModel> {

    @Inject
    lateinit var productService: ProductService

    init {
        DaggerDomainComponents.create().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(productType: ProductType): Observable<ProductsUiModel> =
        productService.getAiringToday(productType.name).map { it.toUiModel(productType) }
}
