package com.endeline.mymovie.ui.top

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.MovieCollectionUiModel.MovieItemUiModel
import com.endeline.domain.usecase.GetTopRatedUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class TopRatedViewModel(getTopRatedUseCase: GetTopRatedUseCase) : ViewModel() {

    private val subscription = CompositeDisposable()

    private val _topRatedLiveData = MutableLiveData<List<MovieItemUiModel>>()

    val topRatedLiveData: LiveData<List<MovieItemUiModel>>
        get() = _topRatedLiveData

    init {
        val disposable = getTopRatedUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ collection ->
                _topRatedLiveData.value = collection.results
            }, Timber::e)

        subscription.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }
}
