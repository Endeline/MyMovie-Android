package com.endeline.mymovie.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.ProductsUiModel.ProductUiModel
import com.endeline.domain.uimodels.ProductDetailsUiModel.ProductionCompaniesUiModel
import com.endeline.domain.uimodels.ProductDetailsUiModel.SpokenLanguagesUiModel
import com.endeline.domain.uimodels.ProductDetailsUiModel.ProductionCountriesUiModel
import com.endeline.domain.uimodels.ProductDetailsUiModel.GenresUiModel
import com.endeline.domain.uimodels.VideoLinkCollectionUiModel.VideoLinkDetailsUiModel
import com.endeline.domain.usecase.GetMovieDetailsUseCase
import com.endeline.domain.usecase.GetRecommendedMovieUseCase
import com.endeline.domain.usecase.GetSimilarMovieUseCase
import com.endeline.domain.usecase.GetVideoLinksUseCase
import com.endeline.mymovie.extensions.ifLet
import com.endeline.mymovie.extensions.ifNotEmpty
import com.endeline.mymovie.ui.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class DetailsViewModel(
    private val getMovieDetailsViewModel: GetMovieDetailsUseCase,
    private val getSimilarMovieUseCase: GetSimilarMovieUseCase,
    private val getRecommendedMovieUseCase: GetRecommendedMovieUseCase,
    private val getVideoLinksUseCase: GetVideoLinksUseCase
) : ViewModel() {

    private val subscriptions = CompositeDisposable()

    private var movieId = Constants.NO_ID

    private val _similarLiveData = MutableLiveData<List<ProductUiModel>>()

    val similarLiveData: LiveData<List<ProductUiModel>>
        get() = _similarLiveData

    private val _recommendedLiveData = MutableLiveData<List<ProductUiModel>>()

    val recommendedLiveData: LiveData<List<ProductUiModel>>
        get() = _recommendedLiveData

    private val _videoLinksLiveData = MutableLiveData<List<VideoLinkDetailsUiModel>>()

    val videoLinksLiveData: LiveData<List<VideoLinkDetailsUiModel>>
        get() = _videoLinksLiveData

    private val _contentLiveData = MutableLiveData<Pair<String, String>>()

    val contentLiveData: LiveData<Pair<String, String>>
        get() = _contentLiveData

    private val _posterLiveData = MutableLiveData<String>()

    val posterLiveData: LiveData<String>
        get() = _posterLiveData

    private val _voteAverage = MutableLiveData<Double>()

    val voteAverageLiveData: LiveData<Double>
        get() = _voteAverage

    private val _popularityLiveData = MutableLiveData<Double>()

    val popularityLiveData: LiveData<Double>
        get() = _popularityLiveData

    private val _genresLiveData = MutableLiveData<List<GenresUiModel>>()

    val genresLiveData: LiveData<List<GenresUiModel>>
        get() = _genresLiveData

    private val _productionCountriesLiveData = MutableLiveData<List<ProductionCountriesUiModel>>()

    val productionCountriesLiveData: LiveData<List<ProductionCountriesUiModel>>
        get() = _productionCountriesLiveData

    private val _spokenLanguagesLiveData = MutableLiveData<List<SpokenLanguagesUiModel>>()

    val spokenLanguagesLiveData: LiveData<List<SpokenLanguagesUiModel>>
        get() = _spokenLanguagesLiveData

    private val _productionCompaniesLiveData = MutableLiveData<List<ProductionCompaniesUiModel>>()

    val productionCompaniesLiveData: LiveData<List<ProductionCompaniesUiModel>>
        get() = _productionCompaniesLiveData

    private val _onDataLoadedLiveData = MutableLiveData<Boolean>()

    val onDataLoadedLiveData: LiveData<Boolean>
        get() = _onDataLoadedLiveData

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }

    fun loadMovieData(id: Int) {
        if (movieId != id) {
            movieId = id

            loadMovieDetails()
            loadSimilarMovies()
            loadRecommendedMovies()
            loadVideoLinks()
        }
    }

    private fun loadMovieDetails() {
        val disposable = getMovieDetailsViewModel(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ details ->
                ifLet(details.title, details.overview) { (title, overview) ->
                    _contentLiveData.value = Pair(title, overview)
                }

                ifNotEmpty(details.posterPath) {
                    _posterLiveData.value = it
                }

                ifNotEmpty(details.voteAverage) {
                    _voteAverage.value = it
                }

                ifNotEmpty(details.popularity) {
                    _popularityLiveData.value = it
                }

                ifNotEmpty(details.genres) {
                    _genresLiveData.value = it
                }

                ifNotEmpty(details.productionCountries) {
                    _productionCountriesLiveData.value = it
                }

                ifNotEmpty(details.spokenLanguages) {
                    _spokenLanguagesLiveData.value = it
                }

                ifNotEmpty(details.productionCompanies) {
                    _productionCompaniesLiveData.value = it
                }

                _onDataLoadedLiveData.value = true
            }, {
                _onDataLoadedLiveData.value = false
                Timber.e(it)
            })

        subscriptions.add(disposable)
    }

    private fun loadSimilarMovies() {
        val disposable = getSimilarMovieUseCase(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ collection ->
                ifNotEmpty(collection.results) { items ->
                    _similarLiveData.postValue(items)
                }
            }, Timber::e)

        subscriptions.add(disposable)
    }

    private fun loadRecommendedMovies() {
        val disposable = getRecommendedMovieUseCase(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ collection ->
                ifNotEmpty(collection.results) { items ->
                    _recommendedLiveData.value = items
                }
            }, Timber::e)

        subscriptions.add(disposable)
    }

    private fun loadVideoLinks() {
        val disposable = getVideoLinksUseCase(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ videoLinks ->
                ifNotEmpty(videoLinks.results) { items ->
                    _videoLinksLiveData.value = items
                }
            }, Timber::e)

        subscriptions.add(disposable)
    }
}
