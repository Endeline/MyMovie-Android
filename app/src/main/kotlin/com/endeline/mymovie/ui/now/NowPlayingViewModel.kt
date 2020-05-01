package com.endeline.mymovie.ui.now

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.MovieCollectionItemUiModel
import com.endeline.domain.usecase.GetNowPlayingUseCase
import com.endeline.mymovie.di.components.DaggerUseCaseComponent
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class NowPlayingViewModel : ViewModel() {

    @Inject
    protected lateinit var getNowPlayingUseCase: GetNowPlayingUseCase

    val movieItemsLiveData = MutableLiveData<List<MovieCollectionItemUiModel>>()

    init {
        DaggerUseCaseComponent.builder().build().inject(this)
    }

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
