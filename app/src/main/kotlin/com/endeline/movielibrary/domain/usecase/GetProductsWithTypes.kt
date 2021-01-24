package com.endeline.movielibrary.domain.usecase

import com.endeline.movielibrary.domain.uimodels.ProductsUiModel
import com.endeline.movielibrary.domain.usecase.types.ObservableUseCaseWithThreeParams
import com.endeline.movielibrary.common.types.ProductType
import com.endeline.movielibrary.common.types.SectionType
import com.endeline.movielibrary.data.service.ProductService
import com.endeline.movielibrary.domain.extensions.toUiModel
import javax.inject.Inject

class GetProductsWithTypes @Inject constructor(private val productService: ProductService):
    ObservableUseCaseWithThreeParams<ProductType, SectionType, ProductsUiModel> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(productType: ProductType, sectionType: SectionType) =
        productService.getProductsWithTypes(productType, sectionType)
            .map { it.toUiModel(productType) }
}
