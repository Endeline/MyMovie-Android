package com.endeline.mymovie.ui.now

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.MovieCollectionUiModel.MovieItemUiModel
import com.endeline.domain.usecase.GetNowPlayingUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class NowPlayingViewModel(getNowPlayingUseCase: GetNowPlayingUseCase) : ViewModel() {

    private val subscription = CompositeDisposable()

    private val _movieItemsLiveData = MutableLiveData<List<MovieItemUiModel>>()

    val movieItemsLiveData: LiveData<List<MovieItemUiModel>>
        get() = _movieItemsLiveData

    init {
        val disposable = getNowPlayingUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ collection ->
                _movieItemsLiveData.value = collection.results
            }, Timber::e)

        subscription.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }
}
