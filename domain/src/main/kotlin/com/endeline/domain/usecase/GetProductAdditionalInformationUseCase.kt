package com.endeline.domain.usecase

import com.endeline.common.ProductType
import com.endeline.common.SectionType
import com.endeline.data.service.ProductService
import com.endeline.domain.di.components.DaggerDomainComponents
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.uimodels.ProductsUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithFourParams
import io.reactivex.Observable
import javax.inject.Inject

class GetProductAdditionalInformationUseCase :
    ObservableUseCaseWithFourParams<ProductType, Int, SectionType, ProductsUiModel> {

    @Inject
    lateinit var productService: ProductService

    init {
        DaggerDomainComponents.create().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(
        productType: ProductType,
        id: Int,
        sectionType: SectionType
    ): Observable<ProductsUiModel> =
        productService.getProductAdditionalInformation(productType, id, sectionType)
            .map { it.toUiModel(ProductType.OTHER) }
}
