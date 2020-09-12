package com.endeline.mymovie.ui.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.MovieCollectionUiModel.MovieItemUiModel
import com.endeline.domain.usecase.GetUpcomingUseCase
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class UpcomingViewModel(getUpcomingUseCase: GetUpcomingUseCase) : ViewModel() {

    private val subscription = CompositeDisposable()

    private val _upcomingLiveData = MutableLiveData<List<MovieItemUiModel>>()

    val upcomingLiveData: LiveData<List<MovieItemUiModel>>
        get() = _upcomingLiveData

    init {
        val disposable = getUpcomingUseCase()
            .subscribe({ collection ->
                _upcomingLiveData.value = collection.results
            }, Timber::e)

        subscription.addAll(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }
}
