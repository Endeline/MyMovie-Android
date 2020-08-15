package com.endeline.mymovie.ui.now

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.MovieCollectionItemUiModel
import com.endeline.domain.usecase.GetNowPlayingUseCase
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class NowPlayingViewModel(private val getNowPlayingUseCase: GetNowPlayingUseCase) : ViewModel() {

    private val movieItemsLiveData = MutableLiveData<List<MovieCollectionItemUiModel>>()

    fun getMovieItemsLiveData(): LiveData<List<MovieCollectionItemUiModel>> = movieItemsLiveData

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
