package com.endeline.mymovie.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.usecase.LoadAllDataUseCase
import timber.log.Timber

class SplashViewModel(private val loadAllDataUseCase: LoadAllDataUseCase) : ViewModel() {

    private val dataLoadedLiveData = MutableLiveData<Boolean>()

    fun getDataLoadedLiveData(): LiveData<Boolean> = dataLoadedLiveData

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
