package com.endeline.movielibrary.domain.usecase

import com.endeline.movielibrary.common.types.ProductType
import com.endeline.movielibrary.data.service.ProductService
import com.endeline.movielibrary.domain.extensions.toUiModel
import com.endeline.movielibrary.domain.uimodels.PersonCreditsUiModel
import com.endeline.movielibrary.domain.usecase.types.ObservableUseCaseWithThreeParams
import io.reactivex.Observable
import javax.inject.Inject

class GetPersonCreditsUseCase @Inject constructor(private val productService: ProductService):
    ObservableUseCaseWithThreeParams<ProductType, Int, PersonCreditsUiModel> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(productType: ProductType, id: Int): Observable<PersonCreditsUiModel> =
        productService.getPersonCredits(productType, id).map { it.toUiModel() }
}
