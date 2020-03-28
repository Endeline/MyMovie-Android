package com.endeline.mymovie.viewmodels

import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.MovieDetailsUiModel
import com.endeline.domain.usecase.AddCacheMovieDetailsUseCase
import com.endeline.domain.usecase.GetMovieDetailsUseCase
import com.endeline.mymovie.di.components.DaggerUseCaseComponent
import javax.inject.Inject

class DetailsViewModel : ViewModel() {

    @Inject
    protected lateinit var getMovieDetailsViewModel: GetMovieDetailsUseCase

    @Inject
    protected lateinit var addCacheMovieDetailsUseCase: AddCacheMovieDetailsUseCase

    init {
        DaggerUseCaseComponent.builder().build().inject(this)
    }

    fun loadMovieDetails(id: Int) = getMovieDetailsViewModel(id)

    fun addMovieDetailsToCache(uiModel: MovieDetailsUiModel) = addCacheMovieDetailsUseCase(uiModel)

}
