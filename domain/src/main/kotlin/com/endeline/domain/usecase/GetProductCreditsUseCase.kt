package com.endeline.domain.usecase

import com.endeline.common.types.ProductType
import com.endeline.common.types.SectionType
import com.endeline.data.service.ProductService
import com.endeline.domain.di.components.DaggerDomainComponents
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.uimodels.ProductCreditsUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithFourParams
import javax.inject.Inject

class GetProductCreditsUseCase :
    ObservableUseCaseWithFourParams<ProductType, Int, SectionType, ProductCreditsUiModel> {

    @Inject
    lateinit var productService: ProductService

    init {
        DaggerDomainComponents.create().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(productType: ProductType, id: Int, sectionType: SectionType) =
        productService.getproductCredits(productType, id, sectionType)
            .map { it.toUiModel(productType) }
}
