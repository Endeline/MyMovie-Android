package com.endeline.mymovie.ui.now

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.MovieCollectionItemUiModel
import com.endeline.domain.usecase.GetNowPlayingUseCase
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class NowPlayingViewModel(
    val getNowPlayingUseCase: GetNowPlayingUseCase
) : ViewModel() {

    val movieItemsLiveData = MutableLiveData<List<MovieCollectionItemUiModel>>()

    @SuppressLint("CheckResult")
    fun loadNowPlaying() {
        getNowPlayingUseCase()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    movieItemsLiveData.postValue(it.results)
                }, Timber::e
            )
    }

}
