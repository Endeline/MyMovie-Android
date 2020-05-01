package com.endeline.mymovie.ui.top

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.MovieCollectionItemUiModel
import com.endeline.domain.usecase.GetTopRatedUseCase
import com.endeline.mymovie.di.components.DaggerUseCaseComponent
import timber.log.Timber
import javax.inject.Inject

class TopRatedViewModel : ViewModel() {

    @Inject
    protected lateinit var getTopRatedUseCase: GetTopRatedUseCase

    val topRatedLiveData = MutableLiveData<List<MovieCollectionItemUiModel>>()

    init {
        DaggerUseCaseComponent.builder().build().inject(this)
    }

    @SuppressLint("CheckResult")
    fun loadTopRated() {
        getTopRatedUseCase()
            .subscribe({
                topRatedLiveData.postValue(it.results)
            }, Timber::e)
    }

}
