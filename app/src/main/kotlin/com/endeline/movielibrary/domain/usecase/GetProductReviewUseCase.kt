package com.endeline.movielibrary.domain.usecase

import com.endeline.movielibrary.common.types.ProductType
import com.endeline.movielibrary.data.service.ProductService
import com.endeline.movielibrary.domain.extensions.toUiModel
import com.endeline.movielibrary.domain.uimodels.ReviewsUiModel
import com.endeline.movielibrary.domain.usecase.types.ObservableUseCaseWithThreeParams
import javax.inject.Inject

class GetProductReviewUseCase @Inject constructor(private val productService: ProductService) :
    ObservableUseCaseWithThreeParams<ProductType, Int, ReviewsUiModel> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(productType: ProductType, id: Int) =
        productService.getProductReviews(productType, id).map { it.toUiModel() }
}
