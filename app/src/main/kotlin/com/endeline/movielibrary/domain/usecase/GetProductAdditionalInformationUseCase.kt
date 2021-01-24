package com.endeline.movielibrary.domain.usecase

import com.endeline.movielibrary.common.types.ProductType
import com.endeline.movielibrary.common.types.SectionType
import com.endeline.movielibrary.data.service.ProductService
import com.endeline.movielibrary.domain.extensions.toUiModel
import com.endeline.movielibrary.domain.uimodels.ProductsUiModel
import com.endeline.movielibrary.domain.usecase.types.ObservableUseCaseWithFourParams
import io.reactivex.Observable
import javax.inject.Inject

class GetProductAdditionalInformationUseCase @Inject constructor(
    private val productService: ProductService
) : ObservableUseCaseWithFourParams<ProductType, Int, SectionType, ProductsUiModel> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(
        productType: ProductType,
        id: Int,
        sectionType: SectionType
    ): Observable<ProductsUiModel> =
        productService.getProductAdditionalInformation(productType, id, sectionType)
            .map { it.toUiModel(ProductType.OTHER) }
}
