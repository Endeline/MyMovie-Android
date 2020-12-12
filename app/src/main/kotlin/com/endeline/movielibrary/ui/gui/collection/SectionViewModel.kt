package com.endeline.movielibrary.ui.gui.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.endeline.common.types.ProductType
import com.endeline.common.types.SectionType
import com.endeline.domain.uimodels.ProductUiModel
import com.endeline.domain.usecase.GetProductsWithTypes
import com.endeline.movielibrary.ui.gui.base.BaseViewModel
import timber.log.Timber

class SectionViewModel(
    private val getProductWithTypes: GetProductsWithTypes
) : BaseViewModel() {

    private val _items = MutableLiveData<List<ProductUiModel>>()

    val items: LiveData<List<ProductUiModel>>
        get() = _items

    fun loadSection(section: String, productType: ProductType) = subscription.add(
        getProductWithTypes(productType, SectionType.valueOf(section))
            .subscribe({ collection ->
                _items.value = collection.results
            }, Timber::e)
    )
}
