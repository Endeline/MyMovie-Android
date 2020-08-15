package com.endeline.mymovie.ui.details

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.*
import com.endeline.domain.usecase.GetMovieDetailsUseCase
import com.endeline.domain.usecase.GetRecommendedMovieUseCase
import com.endeline.domain.usecase.GetSimilarMovieUseCase
import com.endeline.domain.usecase.GetVideoLinksUseCase
import timber.log.Timber

class DetailsViewModel(
    private val getMovieDetailsViewModel: GetMovieDetailsUseCase,
    private val getSimilarMovieUseCase: GetSimilarMovieUseCase,
    private val getRecommendedMovieUseCase: GetRecommendedMovieUseCase,
    private val getVideoLinksUseCase: GetVideoLinksUseCase
) : ViewModel() {

    private val similarLiveData = MutableLiveData<List<MovieCollectionItemUiModel>>()
    private val recommendedLiveData = MutableLiveData<List<MovieCollectionItemUiModel>>()
    private val videoLinksLiveData = MutableLiveData<List<VideoLinkDetailsUiModel>>()
    private val contentLiveData = MutableLiveData<Pair<String, String>>()
    private val posterLiveData = MutableLiveData<String>()
    private val voteAverage = MutableLiveData<Double>()
    private val popularityLiveData = MutableLiveData<Double>()
    private val genresLiveData = MutableLiveData<List<GenresUiModel>>()
    private val productionCountriesLiveData = MutableLiveData<List<ProductionCountriesUiModel>>()
    private val spokenLanguagesLiveData = MutableLiveData<List<SpokenLanguagesUiModel>>()
    private val productionCompaniesLiveData = MutableLiveData<List<ProductionCompaniesUiModel>>()
    private val onDataLoadedLiveData = MutableLiveData<Boolean>()

    fun getSimilarLiveData(): LiveData<List<MovieCollectionItemUiModel>> = similarLiveData

    fun getRecommendedLiveData(): LiveData<List<MovieCollectionItemUiModel>> = recommendedLiveData

    fun getVideoLinksLiveData(): LiveData<List<VideoLinkDetailsUiModel>> = videoLinksLiveData

    fun getContentLiveData(): LiveData<Pair<String, String>> = contentLiveData

    fun getPosterLiveData(): LiveData<String> = posterLiveData

    fun getVoteAverageLiveData(): LiveData<Double> = voteAverage

    fun getPopularityLiveData(): LiveData<Double> = popularityLiveData

    fun getGenresLiveData(): LiveData<List<GenresUiModel>> = genresLiveData

    fun getOnDataLoadedLive(): LiveData<Boolean> = onDataLoadedLiveData

    fun getProductionCountriesLiveData(): LiveData<List<ProductionCountriesUiModel>> =
        productionCountriesLiveData

    fun getSpokenLanguageLiveData(): LiveData<List<SpokenLanguagesUiModel>> =
        spokenLanguagesLiveData

    fun getProductionCompaniesLiveData(): LiveData<List<ProductionCompaniesUiModel>> =
        productionCompaniesLiveData

    fun loadMovieData(movieId: Int) {
        loadMovieDetails(movieId)
        loadSimilarMovies(movieId)
        loadRecommendedMovies(movieId)
        loadVideoLinks(movieId)
    }

    @SuppressLint("CheckResult")
    private fun loadMovieDetails(id: Int) {
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
    private fun loadSimilarMovies(id: Int) {
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
    private fun loadRecommendedMovies(id: Int) {
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
    private fun loadVideoLinks(id: Int) {
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
