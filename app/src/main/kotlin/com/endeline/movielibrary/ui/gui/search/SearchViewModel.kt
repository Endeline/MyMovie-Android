package com.endeline.movielibrary.ui.gui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.SearchUiModel.SearchItemUiModel
import com.endeline.domain.usecase.SearchAllUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SearchViewModel(private val searchAllUseCase: SearchAllUseCase) : ViewModel() {

    private val subscription = CompositeDisposable()

    private val _person = MutableLiveData<List<SearchItemUiModel>>()

    val person: LiveData<List<SearchItemUiModel>>
        get() = _person

    private val _movie = MutableLiveData<List<SearchItemUiModel>>()

    val movie: LiveData<List<SearchItemUiModel>>
        get() = _movie

    private val _tv = MutableLiveData<List<SearchItemUiModel>>()

    val tv: LiveData<List<SearchItemUiModel>>
        get() = _tv

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

                    resultMap[MediaType.person]?.let {
                        _person.value = filter(it)
                    }

                    resultMap[MediaType.movie]?.let {
                        _movie.value = filter(it)
                    }

                    resultMap[MediaType.tv]?.let {
                        _tv.value = filter(it)
                    }
                }
            }, Timber::e)

        subscription.add(disposable)
    }

    private fun filter(items: List<SearchItemUiModel>) =
        items.filter { item ->
            item.profilePath.isNotBlank() || item.backdropPath.isNotBlank() && item.posterPath.isNotBlank()
        }
}
