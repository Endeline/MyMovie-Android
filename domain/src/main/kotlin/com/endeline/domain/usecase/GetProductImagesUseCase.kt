package com.endeline.domain.usecase

import com.endeline.common.ProductType
import com.endeline.data.service.ProductService
import com.endeline.domain.di.components.DaggerDomainComponents
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.uimodels.ImagesUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithThreeParams
import com.endeline.domain.usecase.types.ObservableUseCaseWithTwoParams
import io.reactivex.Observable
import javax.inject.Inject

class GetProductImagesUseCase : ObservableUseCaseWithThreeParams<ProductType, Int, ImagesUiModel> {

    @Inject
    lateinit var productService: ProductService

    init {
        DaggerDomainComponents.create().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(productType: ProductType, id: Int): Observable<ImagesUiModel> =
        productService.getProductImages(productType, id).map { it.toUiModel() }
}
