package com.endeline.movielibrary.domain.usecase

import com.endeline.movielibrary.common.types.ProductType
import com.endeline.movielibrary.data.service.ProductService
import com.endeline.movielibrary.domain.extensions.toUiModel
import com.endeline.movielibrary.domain.uimodels.ImagesUiModel
import com.endeline.movielibrary.domain.usecase.types.ObservableUseCaseWithThreeParams
import io.reactivex.Observable
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(private val productService: ProductService) :
    ObservableUseCaseWithThreeParams<ProductType, Int, ImagesUiModel> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(productType: ProductType, id: Int): Observable<ImagesUiModel> =
        productService.getImages(productType, id).map { it.toUiModel() }
}
