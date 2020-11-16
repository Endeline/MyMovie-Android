package com.endeline.mymovie.ui.gui.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.ProductType
import com.endeline.domain.uimodels.ProductsUiModel
import com.endeline.domain.uimodels.ProductsUiModel.ProductUiModel
import com.endeline.domain.usecase.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.lang.UnsupportedOperationException

class SectionViewModel(
    private val getNowPlayingUseCase: GetNowPlayingUseCase,
    private val getPopularUseCase: GetPopularUseCase,
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val getUpcomingUseCase: GetUpcomingUseCase,
    private val getAiringTodayUseCase: GetAiringTodayUseCase,
    private val getTheAirUseUseCase: GetTheAirUseUseCase
) : ViewModel() {

    private val subscription = CompositeDisposable()

    private val _items = MutableLiveData<List<ProductUiModel>>()

    val items: LiveData<List<ProductUiModel>>
        get() = _items

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }

    fun loadSection(section: String, productType: ProductType) = load(
        when (SectionType.valueOf(section)) {
            SectionType.POPULAR -> (getPopularUseCase(productType))
            SectionType.TOP_RATED -> (getTopRatedUseCase(productType))
            SectionType.NOW_PLAYING -> (getNowPlayingUseCase(productType))
            SectionType.UPCOMING -> (getUpcomingUseCase(productType))
            SectionType.AIRING_TODAY -> (getAiringTodayUseCase(productType))
            SectionType.THE_AIR -> (getTheAirUseUseCase(productType))
            SectionType.NONE -> throw UnsupportedOperationException()
        }
    )


    private fun load(loader: Observable<ProductsUiModel>) {
        val disposable = loader.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ collection ->
                _items.value = collection.results
            }, Timber::e)

        subscription.add(disposable)
    }
}
