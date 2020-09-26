package com.endeline.domain.usecase

import com.endeline.data.services.MovieDbService
import com.endeline.domain.ProductType
import com.endeline.domain.di.components.DaggerRepositoryComponent
import com.endeline.domain.extensions.toUiModel
import com.endeline.domain.uimodels.ProductsUiModel
import com.endeline.domain.usecase.types.ObservableUseCaseWithTwoParams
import io.reactivex.Observable
import javax.inject.Inject

class GetNowPlayingUseCase : ObservableUseCaseWithTwoParams<ProductType, ProductsUiModel> {

    @Inject
    lateinit var repository: MovieDbService

    init {
        DaggerRepositoryComponent.builder().build().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(productType: ProductType): Observable<ProductsUiModel> =
        repository.getNowPlaying(productType.name).map { it.toUiModel(productType) }
}
