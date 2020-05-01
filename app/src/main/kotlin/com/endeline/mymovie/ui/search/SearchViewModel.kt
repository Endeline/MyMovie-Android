package com.endeline.mymovie.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.SearchItemUiModel
import com.endeline.domain.usecase.SearchAllUseCase
import com.endeline.mymovie.di.components.DaggerUseCaseComponent
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SearchViewModel : ViewModel() {

    @Inject
    protected lateinit var searchAllUseCase: SearchAllUseCase

    val personLiveData = MutableLiveData<List<SearchItemUiModel>>()
    val movieLiveData = MutableLiveData<List<SearchItemUiModel>>()
    val tvLiveData = MutableLiveData<List<SearchItemUiModel>>()

    init {
        DaggerUseCaseComponent.builder().build().inject(this)
    }

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
