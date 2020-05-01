package com.endeline.mymovie.ui.details

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.*
import com.endeline.domain.usecase.GetMovieDetailsUseCase
import com.endeline.domain.usecase.GetRecommendedMovieUseCase
import com.endeline.domain.usecase.GetSimilarMovieUseCase
import com.endeline.domain.usecase.GetVideoLinksUseCase
import timber.log.Timber

class DetailsViewModel(
    val getMovieDetailsViewModel: GetMovieDetailsUseCase,
    val getSimilarMovieUseCase: GetSimilarMovieUseCase,
    val getRecommendedMovieUseCase: GetRecommendedMovieUseCase,
    val getVideoLinksUseCase: GetVideoLinksUseCase
) : ViewModel() {

    val similarLiveData = MutableLiveData<List<MovieCollectionItemUiModel>>()
    val recommendedLiveData = MutableLiveData<List<MovieCollectionItemUiModel>>()
    val videoLinksLiveData = MutableLiveData<List<VideoLinkDetailsUiModel>>()
    val contentLiveData = MutableLiveData<Pair<String, String>>()
    val posterLiveData = MutableLiveData<String>()
    val voteAverage = MutableLiveData<Double>()
    val popularityLiveData = MutableLiveData<Double>()
    val genresLiveData = MutableLiveData<List<GenresUiModel>>()
    val productionCountriesLiveData = MutableLiveData<List<ProductionCountriesUiModel>>()
    val spokenLanguagesLiveData = MutableLiveData<List<SpokenLanguagesUiModel>>()
    val productionCompaniesLiveData = MutableLiveData<List<ProductionCompaniesUiModel>>()
    val onDataLoadedLiveData = MutableLiveData<Boolean>()

    @SuppressLint("CheckResult")
    fun loadMovieDetails(id: Int) {
        getMovieDetailsViewModel(id)
            .subscribe(
                {
                    if (!it.title.isNullOrEmpty() && !it.overview.isNullOrEmpty()) {
                        contentLiveData.postValue(
                            Pair(
                                it.title!!,
                                it.overview!!
                            )
                        )
                    }

                    it.posterPath?.let {
                        posterLiveData.postValue(it)
                    }

                    it.voteAverage?.let {
                        voteAverage.postValue(it)
                    }

                    it.popularity?.let {
                        popularityLiveData.postValue(it)
                    }

                    it.genres?.let {
                        genresLiveData.postValue(it)
                    }

                    it.productionCountries?.let {
                        productionCountriesLiveData.postValue(it)
                    }

                    it.spokenLanguages?.let {
                        spokenLanguagesLiveData.postValue(it)
                    }

                    it.productionCompanies?.let {
                        productionCompaniesLiveData.postValue(it)
                    }

                    onDataLoadedLiveData.postValue(true)
                }, Timber::e
            )
    }

    @SuppressLint("CheckResult")
    fun loadSimilarMovies(id: Int) {
        getSimilarMovieUseCase(id)
            .subscribe(
                {
                    it.results?.let {
                        if (!it.isNullOrEmpty()) {
                            similarLiveData.postValue(it)
                        }
                    }
                },
                Timber::e
            )
    }

    @SuppressLint("CheckResult")
    fun loadRecommendedMovies(id: Int) {
        getRecommendedMovieUseCase(id)
            .subscribe(
                {
                    it.results?.let {
                        if (!it.isNullOrEmpty()) {
                            recommendedLiveData.postValue(it)
                        }
                    }
                },
                Timber::e
            )
    }

    @SuppressLint("CheckResult")
    fun loadVideoLinks(id: Int) {
        getVideoLinksUseCase(id)
            .subscribe(
                {
                    it.results?.let {
                        if (!it.isNullOrEmpty()) {
                            videoLinksLiveData.postValue(it)
                        }
                    }
                }, Timber::e
            )
    }

}
