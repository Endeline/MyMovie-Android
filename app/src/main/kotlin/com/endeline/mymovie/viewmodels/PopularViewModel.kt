package com.endeline.mymovie.viewmodels

import androidx.lifecycle.ViewModel
import com.endeline.domain.usecase.GetPopularUseCase
import com.endeline.mymovie.di.components.DaggerUseCaseComponent
import javax.inject.Inject

class PopularViewModel : ViewModel() {

    @Inject
    protected lateinit var getPopularUseCase: GetPopularUseCase

    init {
        DaggerUseCaseComponent.builder().build().inject(this)
    }

    fun getPopular() = getPopularUseCase()

}
