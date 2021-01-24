package com.endeline.movielibrary.ui.gui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.endeline.movielibrary.common.types.ProductType
import com.endeline.movielibrary.domain.uimodels.SearchUiModel.SearchItemUiModel
import com.endeline.movielibrary.domain.usecase.SearchAllUseCase
import com.endeline.movielibrary.ui.gui.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchAllUseCase: SearchAllUseCase) :
    BaseViewModel() {

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
                        ProductType.fromString(item.mediaType)
                    }

                    resultMap[ProductType.PERSON]?.let {
                        _person.value = filter(it)
                    }

                    resultMap[ProductType.MOVIE]?.let {
                        _movie.value = filter(it)
                    }

                    resultMap[ProductType.TV]?.let {
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
