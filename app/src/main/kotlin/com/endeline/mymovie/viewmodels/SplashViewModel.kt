package com.endeline.mymovie.viewmodels

import com.endeline.domain.usecase.LoadAllDataUseCase
import com.endeline.mymovie.di.components.DaggerUseCaseComponent
import javax.inject.Inject

class SplashViewModel {

    @Inject
    protected lateinit var loadAllDataUseCase: LoadAllDataUseCase

    init {
        DaggerUseCaseComponent.builder().build().inject(this)
    }

    fun loadData() = loadAllDataUseCase()

}
