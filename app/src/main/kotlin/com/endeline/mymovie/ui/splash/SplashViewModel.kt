package com.endeline.mymovie.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.endeline.domain.usecase.LoadAllDataUseCase
import com.endeline.mymovie.di.components.DaggerUseCaseComponent
import timber.log.Timber
import javax.inject.Inject

class SplashViewModel {

    @Inject
    protected lateinit var loadAllDataUseCase: LoadAllDataUseCase

    val dataLoadedLiveData = MutableLiveData<Boolean>()

    init {
        DaggerUseCaseComponent.builder().build().inject(this)
    }

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
