package com.endeline.mymovie.ui.popular

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.MovieCollectionItemUiModel
import com.endeline.domain.usecase.GetPopularUseCase
import com.endeline.mymovie.di.components.DaggerUseCaseComponent
import timber.log.Timber
import javax.inject.Inject

class PopularViewModel : ViewModel() {

    @Inject
    protected lateinit var getPopularUseCase: GetPopularUseCase

    val popularLiveData = MutableLiveData<List<MovieCollectionItemUiModel>>()

    init {
        DaggerUseCaseComponent.builder().build().inject(this)
    }

    @SuppressLint("CheckResult")
    fun loadPopular() {
        getPopularUseCase().subscribe(
            {
                popularLiveData.postValue(it.results)
            }, Timber::e
        )
    }

}
