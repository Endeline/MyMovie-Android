package com.endeline.domain.usecase

import com.endeline.common.ProductType
import com.endeline.data.service.ProductService
import com.endeline.domain.di.components.DaggerDomainComponents
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.uimodels.CreditsUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithThreeParams
import io.reactivex.Observable
import javax.inject.Inject

class GetProductCreditsUseCase :
    ObservableUseCaseWithThreeParams<ProductType, Int, CreditsUiModel> {

    @Inject
    lateinit var productService: ProductService

    init {
        DaggerDomainComponents.create().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(productType: ProductType, id: Int): Observable<CreditsUiModel> =
        productService.getProductCredits(productType, id).map { it.toUiModel() }
}
