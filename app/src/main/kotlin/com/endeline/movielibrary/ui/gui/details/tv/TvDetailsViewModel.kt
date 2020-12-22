package com.endeline.movielibrary.ui.gui.details.tv

import com.endeline.common.types.ProductType
import com.endeline.domain.usecase.GetProductDetailsUseCase
import com.endeline.movielibrary.ui.gui.base.BaseViewModel
import timber.log.Timber

class TvDetailsViewModel(
    private val getProductDetailsUseCase: GetProductDetailsUseCase
) : BaseViewModel() {

    fun loadData(productId: Int) = subscription.add(
        getProductDetailsUseCase(ProductType.TV, productId)
            .subscribe({
                Timber.d("Wazne Data loaded: $it")
            }, Timber::e)
    )
}