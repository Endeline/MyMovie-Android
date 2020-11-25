package com.endeline.domain.usecase

import com.endeline.data.service.ProductService
import com.endeline.domain.di.components.DaggerDomainComponents
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.uimodels.ImagesUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithTwoParams
import javax.inject.Inject

class GetProductImagesUseCase : ObservableUseCaseWithTwoParams<Int, ImagesUiModel> {

    @Inject
    lateinit var productService: ProductService

    init {
        DaggerDomainComponents.create().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(id: Int) = productService.getVideoImages(id).map { it.toUiModel() }
}
