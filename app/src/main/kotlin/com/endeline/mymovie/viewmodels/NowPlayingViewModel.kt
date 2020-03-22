package com.endeline.mymovie.viewmodels

import androidx.lifecycle.ViewModel
import com.endeline.domain.usecase.GetNowPlayingUseCase
import com.endeline.mymovie.di.components.DaggerUseCaseComponent
import javax.inject.Inject

class NowPlayingViewModel : ViewModel() {

    @Inject
    protected lateinit var getNowPlayingUseCase: GetNowPlayingUseCase

    init {
        DaggerUseCaseComponent.builder().build().inject(this)
    }

    fun getNowPlaying() = getNowPlayingUseCase()

}
