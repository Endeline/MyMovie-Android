package com.endeline.mymovie.viewmodels

import androidx.lifecycle.ViewModel
import com.endeline.domain.usecase.GetMovieDetailsUseCase
import com.endeline.domain.usecase.GetRecommendedMovieUseCase
import com.endeline.domain.usecase.GetSimilarMovieUseCase
import com.endeline.domain.usecase.GetVideoLinksUseCase
import com.endeline.mymovie.di.components.DaggerUseCaseComponent
import javax.inject.Inject

class DetailsViewModel : ViewModel() {

    @Inject
    protected lateinit var getMovieDetailsViewModel: GetMovieDetailsUseCase

    @Inject
    protected lateinit var getSimilarMovieUseCase: GetSimilarMovieUseCase

    @Inject
    protected lateinit var getRecommendedMovieUseCase: GetRecommendedMovieUseCase

    @Inject
    protected lateinit var getVideoLinksUseCase: GetVideoLinksUseCase

    init {
        DaggerUseCaseComponent.builder().build().inject(this)
    }

    fun loadMovieDetails(id: Int) = getMovieDetailsViewModel(id)

    fun loadSimilarMovies(id: Int) = getSimilarMovieUseCase(id)

    fun loadRecommendedMovies(id: Int) = getRecommendedMovieUseCase(id)

    fun loadVideoLinks(id: Int) = getVideoLinksUseCase(id)

}
