package com.endeline.mymovie.ui.upcoming

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.MovieCollectionItemUiModel
import com.endeline.domain.usecase.GetUpcomingUseCase
import timber.log.Timber

class UpcomingViewModel(private val getUpcomingUseCase: GetUpcomingUseCase) : ViewModel() {

    val upcomingLiveData = MutableLiveData<List<MovieCollectionItemUiModel>>()

    @SuppressLint("CheckResult")
    fun loadUpcoming() {
        getUpcomingUseCase()
            .subscribe({
                upcomingLiveData.postValue(it.results)
            }, Timber::e)
    }

}