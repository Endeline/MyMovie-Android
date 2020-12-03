package com.endeline.domain.usecase

import com.endeline.common.ProductType
import com.endeline.data.service.ProductService
import com.endeline.domain.di.components.DaggerDomainComponents
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.uimodels.VideoLinkCollectionUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithThreeParams
import com.endeline.domain.usecase.types.ObservableUseCaseWithTwoParams
import io.reactivex.Observable
import javax.inject.Inject

class GetProductVideoLinksUseCase : ObservableUseCaseWithThreeParams<ProductType, Int, VideoLinkCollectionUiModel> {

    @Inject
    lateinit var productService: ProductService

    init {
        DaggerDomainComponents.create().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(productType: ProductType, id: Int)=
        productService.getProductVideoLinks(productType, id).map { it.toUiModel() }
}
