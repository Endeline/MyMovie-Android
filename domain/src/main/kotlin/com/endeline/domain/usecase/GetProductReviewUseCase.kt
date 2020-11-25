package com.endeline.domain.usecase

import com.endeline.data.service.ProductService
import com.endeline.domain.di.components.DaggerDomainComponents
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.uimodels.ReviewsUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithTwoParams
import javax.inject.Inject

class GetProductReviewUseCase : ObservableUseCaseWithTwoParams<Int, ReviewsUiModel> {

    @Inject
    lateinit var productService: ProductService

    init {
        DaggerDomainComponents.create().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(id: Int) = productService.getProductReviews(id).map { it.toUiModel() }
}
