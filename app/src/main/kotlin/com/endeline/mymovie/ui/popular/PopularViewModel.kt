package com.endeline.mymovie.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.MovieCollectionUiModel.MovieItemUiModel
import com.endeline.domain.usecase.GetPopularUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class PopularViewModel(getPopularUseCase: GetPopularUseCase) : ViewModel() {

    private val subscription = CompositeDisposable()

    private val _popularLiveData = MutableLiveData<List<MovieItemUiModel>>()

    val popularLiveData: LiveData<List<MovieItemUiModel>>
        get() = _popularLiveData

    init {
        val disposable = getPopularUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ collection ->
                _popularLiveData.value = collection.results
            }, Timber::e)

        subscription.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }
}
