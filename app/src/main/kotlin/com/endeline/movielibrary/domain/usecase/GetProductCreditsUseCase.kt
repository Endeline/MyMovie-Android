package com.endeline.movielibrary.domain.usecase

import com.endeline.movielibrary.common.types.ProductType
import com.endeline.movielibrary.common.types.SectionType
import com.endeline.movielibrary.data.service.ProductService
import com.endeline.movielibrary.domain.extensions.toUiModel
import com.endeline.movielibrary.domain.uimodels.ProductCreditsUiModel
import com.endeline.movielibrary.domain.usecase.types.ObservableUseCaseWithFourParams
import javax.inject.Inject

class GetProductCreditsUseCase @Inject constructor(private val productService: ProductService):
    ObservableUseCaseWithFourParams<ProductType, Int, SectionType, ProductCreditsUiModel> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(productType: ProductType, id: Int, sectionType: SectionType) =
        productService.getproductCredits(productType, id, sectionType)
            .map { it.toUiModel(productType) }
}
