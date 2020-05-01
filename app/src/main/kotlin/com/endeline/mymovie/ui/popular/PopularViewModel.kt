package com.endeline.mymovie.ui.popular

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.MovieCollectionItemUiModel
import com.endeline.domain.usecase.GetPopularUseCase
import timber.log.Timber

class PopularViewModel(
    val getPopularUseCase: GetPopularUseCase
) : ViewModel() {

    val popularLiveData = MutableLiveData<List<MovieCollectionItemUiModel>>()

    @SuppressLint("CheckResult")
    fun loadPopular() {
        getPopularUseCase().subscribe(
            {
                popularLiveData.postValue(it.results)
            }, Timber::e
        )
    }

}
