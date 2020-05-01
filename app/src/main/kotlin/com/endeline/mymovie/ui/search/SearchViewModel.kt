package com.endeline.mymovie.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.SearchItemUiModel
import com.endeline.domain.usecase.SearchAllUseCase
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SearchViewModel(val searchAllUseCase: SearchAllUseCase) : ViewModel() {

    val personLiveData = MutableLiveData<List<SearchItemUiModel>>()
    val movieLiveData = MutableLiveData<List<SearchItemUiModel>>()
    val tvLiveData = MutableLiveData<List<SearchItemUiModel>>()

    fun search(query: String) =
        searchAllUseCase(query)
            .subscribeOn(Schedulers.io())
            .subscribe({
                val personResult = mutableListOf<SearchItemUiModel>()
                val movieResult = mutableListOf<SearchItemUiModel>()
                val tvResult = mutableListOf<SearchItemUiModel>()

                it.results?.forEach {
                    when (MediaType.fromString(it.mediaType)) {
                        MediaType.person -> personResult.add(it)
                        MediaType.movie -> movieResult.add(it)
                        MediaType.tv -> tvResult.add(it)
                    }
                }

                personLiveData.postValue(personResult)
                movieLiveData.postValue(movieResult)
                tvLiveData.postValue(tvResult)
            }, Timber::e)

}
