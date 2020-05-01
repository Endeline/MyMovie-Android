package com.endeline.mymovie.ui.top

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.MovieCollectionItemUiModel
import com.endeline.domain.usecase.GetTopRatedUseCase
import timber.log.Timber

class TopRatedViewModel(val getTopRatedUseCase: GetTopRatedUseCase) : ViewModel() {

    val topRatedLiveData = MutableLiveData<List<MovieCollectionItemUiModel>>()

    @SuppressLint("CheckResult")
    fun loadTopRated() {
        getTopRatedUseCase()
            .subscribe({
                topRatedLiveData.postValue(it.results)
            }, Timber::e)
    }

}
