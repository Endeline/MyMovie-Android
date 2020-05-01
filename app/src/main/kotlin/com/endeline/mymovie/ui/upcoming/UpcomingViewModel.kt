package com.endeline.mymovie.ui.upcoming

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.MovieCollectionItemUiModel
import com.endeline.domain.usecase.GetUpcomingUseCase
import com.endeline.mymovie.di.components.DaggerUseCaseComponent
import timber.log.Timber
import javax.inject.Inject

class UpcomingViewModel : ViewModel() {

    @Inject
    protected lateinit var getUpcomingUseCase: GetUpcomingUseCase

    val upcomingLiveData = MutableLiveData<List<MovieCollectionItemUiModel>>()

    init {
        DaggerUseCaseComponent.builder().build().inject(this)
    }

    @SuppressLint("CheckResult")
    fun loadUpcoming() {
        getUpcomingUseCase()
            .subscribe({
                upcomingLiveData.postValue(it.results)
            }, Timber::e)
    }

}
