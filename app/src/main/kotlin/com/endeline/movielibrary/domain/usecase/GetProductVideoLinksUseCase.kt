package com.endeline.movielibrary.domain.usecase

import com.endeline.movielibrary.common.types.ProductType
import com.endeline.movielibrary.data.service.ProductService
import com.endeline.movielibrary.domain.extensions.toUiModel
import com.endeline.movielibrary.domain.uimodels.VideoLinkCollectionUiModel
import com.endeline.movielibrary.domain.usecase.types.ObservableUseCaseWithThreeParams
import javax.inject.Inject

class GetProductVideoLinksUseCase @Inject constructor(private val productService: ProductService) :
    ObservableUseCaseWithThreeParams<ProductType, Int, VideoLinkCollectionUiModel> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(productType: ProductType, id: Int) =
        productService.getProductVideoLinks(productType, id).map { it.toUiModel() }
}
