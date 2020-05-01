package com.endeline.mymovie.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.usecase.LoadAllDataUseCase
import timber.log.Timber

class SplashViewModel(val loadAllDataUseCase: LoadAllDataUseCase) :
    ViewModel() {

    val dataLoadedLiveData = MutableLiveData<Boolean>()

    @SuppressLint("CheckResult")
    fun loadData() {
        loadAllDataUseCase()
            .subscribe({
                dataLoadedLiveData.postValue(true)
            }, {
                Timber.e(it)
                dataLoadedLiveData.postValue(false)
            })
    }

}
