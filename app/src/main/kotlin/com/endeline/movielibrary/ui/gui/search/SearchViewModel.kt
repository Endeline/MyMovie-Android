package com.endeline.movielibrary.ui.gui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.endeline.domain.uimodels.SearchUiModel.SearchItemUiModel
import com.endeline.domain.usecase.SearchAllUseCase
import com.endeline.movielibrary.ui.gui.base.BaseViewModel
import timber.log.Timber

class SearchViewModel(private val searchAllUseCase: SearchAllUseCase) : BaseViewModel() {

    private val _person = MutableLiveData<List<SearchItemUiModel>>()

    val person: LiveData<List<SearchItemUiModel>>
        get() = _person

    private val _movie = MutableLiveData<List<SearchItemUiModel>>()

    val movie: LiveData<List<SearchItemUiModel>>
        get() = _movie

    private val _tv = MutableLiveData<List<SearchItemUiModel>>()

    val tv: LiveData<List<SearchItemUiModel>>
        get() = _tv

    fun search(query: String) = subscription.add(
        searchAllUseCase(query)
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
    )

    private fun filter(items: List<SearchItemUiModel>) =
        items.filter { item ->
            item.profilePath.isNotBlank() || item.backdropPath.isNotBlank() && item.posterPath.isNotBlank()
        }
}
