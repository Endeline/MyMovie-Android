package com.endeline.mymovie.ui.gui.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.common.ProductType
import com.endeline.common.SectionType
import com.endeline.domain.uimodels.ProductsUiModel
import com.endeline.domain.uimodels.ProductsUiModel.ProductUiModel
import com.endeline.domain.usecase.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SectionViewModel(
    private val getProductWithTypes: GetProductWithTypes
) : ViewModel() {

    private val subscription = CompositeDisposable()

    private val _items = MutableLiveData<List<ProductUiModel>>()

    val items: LiveData<List<ProductUiModel>>
        get() = _items

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }

    fun loadSection(section: String, productType: ProductType) =
        load(getProductWithTypes(productType, SectionType.valueOf(section)))

    private fun load(loader: Observable<ProductsUiModel>) {
        val disposable = loader.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ collection ->
                _items.value = collection.results
            }, Timber::e)

        subscription.add(disposable)
    }
}
