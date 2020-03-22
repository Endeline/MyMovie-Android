package com.endeline.mymovie.viewmodels

import androidx.lifecycle.ViewModel
import com.endeline.domain.usecase.GetLatestUseCase
import com.endeline.mymovie.di.components.DaggerUseCaseComponent
import javax.inject.Inject

class LatestViewModel : ViewModel() {

    @Inject
    protected lateinit var getLatestUseCase: GetLatestUseCase

    init {
        DaggerUseCaseComponent.builder().build().inject(this)
    }

    fun getLatest() = getLatestUseCase()
}
