package com.endeline.mymovie.ui.gui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.CreditsUiModel.PersonUiModel
import com.endeline.domain.uimodels.ImagesUiModel.ImageUiModel
import com.endeline.domain.uimodels.ProductsUiModel.ProductUiModel
import com.endeline.domain.uimodels.ReviewsUiModel.ReviewUiModel
import com.endeline.domain.uimodels.VideoLinkCollectionUiModel.VideoLinkDetailsUiModel
import com.endeline.domain.usecase.*
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
    private val getVideoLinksUseCase: GetVideoLinksUseCase,
    private val getProductImagesUseCase: GetProductImagesUseCase,
    private val getProductReviewUseCase: GetProductReviewUseCase,
    private val getProductCreditsUseCase: GetProductCreditsUseCase
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

    private val _voteAverage = MutableLiveData<Double>()

    val voteAverageLiveData: LiveData<Double>
        get() = _voteAverage

    private val _popularityLiveData = MutableLiveData<Double>()

    val popularityLiveData: LiveData<Double>
        get() = _popularityLiveData

    private val _genresLiveData = MutableLiveData<List<String>>()

    val genresLiveData: LiveData<List<String>>
        get() = _genresLiveData

    private val _productionCountriesLiveData = MutableLiveData<List<String>>()

    val productionCountriesLiveData: LiveData<List<String>>
        get() = _productionCountriesLiveData

    private val _spokenLanguagesLiveData = MutableLiveData<List<String>>()

    val spokenLanguagesLiveData: LiveData<List<String>>
        get() = _spokenLanguagesLiveData

    private val _productionCompaniesLiveData = MutableLiveData<List<String>>()

    val productionCompaniesLiveData: LiveData<List<String>>
        get() = _productionCompaniesLiveData

    private val _onDataLoadedLiveData = MutableLiveData<Boolean>()

    val onDataLoadedLiveData: LiveData<Boolean>
        get() = _onDataLoadedLiveData

    private val _backdropsLiveData = MutableLiveData<List<ImageUiModel>>()

    val backdropsLiveData: LiveData<List<ImageUiModel>>
        get() = _backdropsLiveData

    private val _reviewsLiveData = MutableLiveData<List<ReviewUiModel>>()

    val reviewsLiveData: LiveData<List<ReviewUiModel>>
        get() = _reviewsLiveData

    private val _castLiveData = MutableLiveData<List<PersonUiModel>>()

    val castLiveData: LiveData<List<PersonUiModel>>
        get() = _castLiveData

    private val _crewLiveData = MutableLiveData<List<PersonUiModel>>()

    val crewLiveData: LiveData<List<PersonUiModel>>
        get() = _crewLiveData

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
            loadVideoImages()
            loadReviews()
            loadCredits()
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

                ifNotEmpty(details.voteAverage) { vote ->
                    if (vote > 0) {
                        _voteAverage.value = vote
                    }
                }

                ifNotEmpty(details.popularity) {
                    _popularityLiveData.value = it
                }

                ifNotEmpty(details.genres) {
                    _genresLiveData.value = it.map { it.name }
                }

                ifNotEmpty(details.productionCountries) {
                    _productionCountriesLiveData.value = it.map { it.name }
                }

                ifNotEmpty(details.spokenLanguages) {
                    _spokenLanguagesLiveData.value = it.map { it.name }
                }

                ifNotEmpty(details.productionCompanies) {
                    _productionCompaniesLiveData.value = it.map { it.name }
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
            .filter { it.results.isNotEmpty() }
            .subscribe({
                _similarLiveData.value = it.results
            }, Timber::e)

        subscriptions.add(disposable)
    }

    private fun loadRecommendedMovies() {
        val disposable = getRecommendedMovieUseCase(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.results.isNotEmpty() }
            .subscribe({
                _recommendedLiveData.value = it.results
            }, Timber::e)

        subscriptions.add(disposable)
    }

    private fun loadVideoLinks() {
        val disposable = getVideoLinksUseCase(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.results.isNotEmpty() }
            .subscribe({
                _videoLinksLiveData.value = it.results
            }, Timber::e)

        subscriptions.add(disposable)
    }

    private fun loadVideoImages() {
        val disposable = getProductImagesUseCase(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.backdrops.isNotEmpty() }
            .subscribe({
                _backdropsLiveData.value = it.backdrops
            }, Timber::e)

        subscriptions.add(disposable)
    }

    private fun loadReviews() {
        val disposable = getProductReviewUseCase(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.result.isNotEmpty() }
            .subscribe({
                _reviewsLiveData.value = it.result
            }, Timber::e)

        subscriptions.add(disposable)
    }

    private fun loadCredits() {
        val disposable = getProductCreditsUseCase(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                ifNotEmpty(response.cast) {
                    _castLiveData.value = it.filter { it.profilePath.isNotBlank() }
                }

                ifNotEmpty(response.crew) {
                    _crewLiveData.value = it.filter { it.profilePath.isNotBlank() }
                }
            }, Timber::e)

        subscriptions.add(disposable)
    }
}
