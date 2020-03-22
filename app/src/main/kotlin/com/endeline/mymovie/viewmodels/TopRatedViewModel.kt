package com.endeline.mymovie.viewmodels

import androidx.lifecycle.ViewModel
import com.endeline.domain.usecase.GetTopRatedUseCase
import com.endeline.mymovie.di.components.DaggerUseCaseComponent
import javax.inject.Inject

class TopRatedViewModel : ViewModel() {

    @Inject
    protected lateinit var getTopRatedUseCase: GetTopRatedUseCase

    init {
        DaggerUseCaseComponent.builder().build().inject(this)
    }

    fun getTopRated() = getTopRatedUseCase()
}
