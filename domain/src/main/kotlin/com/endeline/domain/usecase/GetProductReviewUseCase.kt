package com.endeline.domain.usecase

import com.endeline.common.types.ProductType
import com.endeline.data.service.ProductService
import com.endeline.domain.di.components.DaggerDomainComponents
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.uimodels.ReviewsUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithThreeParams
import javax.inject.Inject

class GetProductReviewUseCase : ObservableUseCaseWithThreeParams<ProductType, Int, ReviewsUiModel> {

    @Inject
    lateinit var productService: ProductService

    init {
        DaggerDomainComponents.create().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(productType: ProductType, id: Int) =
        productService.getProductReviews(productType, id).map { it.toUiModel() }
}
