package com.endeline.mymovie.ui.gui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.common.ProductType
import com.endeline.common.SectionType
import com.endeline.domain.uimodels.ImagesUiModel.ImageUiModel
import com.endeline.domain.uimodels.PersonUiModel
import com.endeline.domain.uimodels.ProductsUiModel.ProductUiModel
import com.endeline.domain.uimodels.ReviewsUiModel.ReviewUiModel
import com.endeline.domain.uimodels.VideoLinkCollectionUiModel.VideoLinkDetailsUiModel
import com.endeline.domain.usecase.*
import com.endeline.mymovie.extensions.ifLet
import com.endeline.mymovie.extensions.ifNotEmpty
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class DetailsViewModel(
    private val getMovieDetailsViewModel: GetMovieDetailsUseCase,
    private val getProductAdditionalInformationUseCase: GetProductAdditionalInformationUseCase,
    private val getVideoLinksUseCase: GetVideoLinksUseCase,
    private val getProductImagesUseCase: GetProductImagesUseCase,
    private val getProductReviewUseCase: GetProductReviewUseCase,
    private val getProductCreditsUseCase: GetProductCreditsUseCase
) : ViewModel() {

    private val subscriptions = CompositeDisposable()

    private val _similar = MutableLiveData<List<ProductUiModel>>()

    val similar: LiveData<List<ProductUiModel>>
        get() = _similar

    private val _recommended = MutableLiveData<List<ProductUiModel>>()

    val recommended: LiveData<List<ProductUiModel>>
        get() = _recommended

    private val _videoLinks = MutableLiveData<List<VideoLinkDetailsUiModel>>()

    val videoLinks: LiveData<List<VideoLinkDetailsUiModel>>
        get() = _videoLinks

    private val _content = MutableLiveData<Pair<String, String>>()

    val content: LiveData<Pair<String, String>>
        get() = _content

    private val _voteAverage = MutableLiveData<Double>()

    val voteAverage: LiveData<Double>
        get() = _voteAverage

    private val _popularity = MutableLiveData<Double>()

    val popularity: LiveData<Double>
        get() = _popularity

    private val _genres = MutableLiveData<List<String>>()

    val genres: LiveData<List<String>>
        get() = _genres

    private val _productionCountries = MutableLiveData<List<String>>()

    val productionCountries: LiveData<List<String>>
        get() = _productionCountries

    private val _spokenLanguages = MutableLiveData<List<String>>()

    val spokenLanguages: LiveData<List<String>>
        get() = _spokenLanguages

    private val _productionCompanies = MutableLiveData<List<String>>()

    val productionCompanies: LiveData<List<String>>
        get() = _productionCompanies

    private val _onDataLoaded = MutableLiveData<Boolean>()

    val onDataLoaded: LiveData<Boolean>
        get() = _onDataLoaded

    private val _backdrops = MutableLiveData<List<ImageUiModel>>()

    val backdrops: LiveData<List<ImageUiModel>>
        get() = _backdrops

    private val _reviews = MutableLiveData<List<ReviewUiModel>>()

    val reviews: LiveData<List<ReviewUiModel>>
        get() = _reviews

    private val _cast = MutableLiveData<List<PersonUiModel>>()

    val cast: LiveData<List<PersonUiModel>>
        get() = _cast

    private val _crew = MutableLiveData<List<PersonUiModel>>()

    val crew: LiveData<List<PersonUiModel>>
        get() = _crew

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }

    fun loadMovieData(movieId: Int) {
        loadMovieDetails(movieId)
        loadSimilarMovies(movieId)
        loadRecommendedMovies(movieId)
        loadVideoLinks(movieId)
        loadVideoImages(movieId)
        loadReviews(movieId)
        loadCredits(movieId)
    }

    private fun loadMovieDetails(movieId: Int) {
        val disposable = getMovieDetailsViewModel(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ details ->
                ifLet(details.title, details.overview) { (title, overview) ->
                    _content.value = Pair(title, overview)
                }

                ifNotEmpty(details.voteAverage) { vote ->
                    if (vote > 0) {
                        _voteAverage.value = vote
                    }
                }

                ifNotEmpty(details.popularity) {
                    _popularity.value = it
                }

                ifNotEmpty(details.genres) {
                    _genres.value = it.map { it.name }
                }

                ifNotEmpty(details.productionCountries) {
                    _productionCountries.value = it.map { it.name }
                }

                ifNotEmpty(details.spokenLanguages) {
                    _spokenLanguages.value = it.map { it.name }
                }

                ifNotEmpty(details.productionCompanies) {
                    _productionCompanies.value = it.map { it.name }
                }

                _onDataLoaded.value = true
            }, {
                _onDataLoaded.value = false
                Timber.e(it)
            })

        subscriptions.add(disposable)
    }

    private fun loadSimilarMovies(movieId: Int) {
        val disposable =
            getProductAdditionalInformationUseCase(ProductType.MOVIE, movieId, SectionType.SIMILAR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter { it.results.isNotEmpty() }
                .subscribe({
                    _similar.value = it.results
                }, Timber::e)

        subscriptions.add(disposable)
    }

    private fun loadRecommendedMovies(movieId: Int) {
        val disposable = getProductAdditionalInformationUseCase(
            ProductType.MOVIE,
            movieId,
            SectionType.RECOMMENDATIONS
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.results.isNotEmpty() }
            .subscribe({
                _recommended.value = it.results
            }, Timber::e)

        subscriptions.add(disposable)
    }

    private fun loadVideoLinks(movieId: Int) {
        val disposable = getVideoLinksUseCase(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.results.isNotEmpty() }
            .subscribe({
                _videoLinks.value = it.results
            }, Timber::e)

        subscriptions.add(disposable)
    }

    private fun loadVideoImages(movieId: Int) {
        val disposable = getProductImagesUseCase(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.backdrops.isNotEmpty() }
            .subscribe({
                _backdrops.value = it.backdrops
            }, Timber::e)

        subscriptions.add(disposable)
    }

    private fun loadReviews(movieId: Int) {
        val disposable = getProductReviewUseCase(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.result.isNotEmpty() }
            .subscribe({
                _reviews.value = it.result
            }, Timber::e)

        subscriptions.add(disposable)
    }

    private fun loadCredits(movieId: Int) {
        val disposable = getProductCreditsUseCase(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                ifNotEmpty(response.cast) {
                    _cast.value = it.filter { it.profilePath.isNotBlank() }
                }

                ifNotEmpty(response.crew) {
                    _crew.value = it.filter { it.profilePath.isNotBlank() }
                }
            }, Timber::e)

        subscriptions.add(disposable)
    }
}
