package com.endeline.movielibrary.domain.usecase

import com.endeline.movielibrary.data.service.ProductService
import com.endeline.movielibrary.domain.extensions.toUiModel
import com.endeline.movielibrary.domain.uimodels.PersonUiModel
import com.endeline.movielibrary.domain.usecase.types.ObservableUseCaseWithTwoParams
import io.reactivex.Observable
import javax.inject.Inject

class GetPersonDetailsUseCase @Inject constructor(private val productService: ProductService) :
    ObservableUseCaseWithTwoParams<Int, PersonUiModel> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(id: Int): Observable<PersonUiModel> =
        productService.getPersonDetails(id).map { it.toUiModel() }
}
