package com.endeline.mymovie.viewmodels

import androidx.lifecycle.ViewModel
import com.endeline.domain.usecase.GetUpcomingUseCase
import com.endeline.mymovie.di.components.DaggerUseCaseComponent
import javax.inject.Inject

class UpcomingViewModel : ViewModel() {

    @Inject
    protected lateinit var getUpcomingUseCase: GetUpcomingUseCase

    init {
        DaggerUseCaseComponent.builder().build().inject(this)
    }

    fun getUpcoming() = getUpcomingUseCase()
}
