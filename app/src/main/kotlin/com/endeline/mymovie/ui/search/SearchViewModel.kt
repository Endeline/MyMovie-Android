package com.endeline.mymovie.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.SearchAllUiModel.SearchItemUiModel
import com.endeline.domain.usecase.SearchAllUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SearchViewModel(private val searchAllUseCase: SearchAllUseCase) : ViewModel() {

    private val subscription = CompositeDisposable()

    private val _personLiveData = MutableLiveData<List<SearchItemUiModel>>()

    val personLiveData: LiveData<List<SearchItemUiModel>>
        get() = _personLiveData

    private val _movieLiveData = MutableLiveData<List<SearchItemUiModel>>()

    val movieLiveData: LiveData<List<SearchItemUiModel>>
        get() = _movieLiveData

    private val _tvLiveData = MutableLiveData<List<SearchItemUiModel>>()

    val tvLiveData: LiveData<List<SearchItemUiModel>>
        get() = _tvLiveData

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }

    fun search(query: String) {
        val disposable = searchAllUseCase(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ searchResult ->
                if (searchResult.results.isNotEmpty()) {
                    val resultMap = searchResult.results.groupBy { item ->
                        MediaType.fromString(item.mediaType)
                    }

                    _personLiveData.value = resultMap[MediaType.person]
                    _movieLiveData.value = resultMap[MediaType.movie]
                    _tvLiveData.value = resultMap[MediaType.tv]
                }
            }, Timber::e)

        subscription.add(disposable)
    }
}
