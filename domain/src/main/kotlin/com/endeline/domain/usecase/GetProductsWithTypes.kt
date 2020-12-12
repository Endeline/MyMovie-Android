package com.endeline.domain.usecase

import com.endeline.common.types.ProductType
import com.endeline.common.types.SectionType
import com.endeline.data.service.ProductService
import com.endeline.domain.di.components.DaggerDomainComponents
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.uimodels.ProductsUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithThreeParams
import javax.inject.Inject

class GetProductsWithTypes :
    ObservableUseCaseWithThreeParams<ProductType, SectionType, ProductsUiModel> {

    @Inject
    lateinit var productService: ProductService

    init {
        DaggerDomainComponents.create().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(productType: ProductType, sectionType: SectionType) =
        productService.getProductsWithTypes(productType, sectionType)
            .map { it.toUiModel(productType) }
}
