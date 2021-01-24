package com.endeline.movielibrary.ui.gui.details.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.endeline.movielibrary.common.types.ProductType
import com.endeline.movielibrary.common.types.SectionType
import com.endeline.movielibrary.domain.uimodels.ImagesUiModel.ImageUiModel
import com.endeline.movielibrary.domain.uimodels.PersonUiModel
import com.endeline.movielibrary.domain.uimodels.ProductUiModel
import com.endeline.movielibrary.domain.uimodels.ReviewsUiModel.ReviewUiModel
import com.endeline.movielibrary.domain.uimodels.VideoLinkCollectionUiModel.VideoLinkDetailsUiModel
import com.endeline.movielibrary.ui.extensions.ifLet
import com.endeline.movielibrary.ui.extensions.ifNotEmpty
import com.endeline.movielibrary.Constants.Values.VALUE_ZERO
import com.endeline.movielibrary.domain.usecase.*
import com.endeline.movielibrary.ui.gui.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val getProductDetailsViewModel: GetProductDetailsUseCase,
    private val getProductAdditionalInformationUseCase: GetProductAdditionalInformationUseCase,
    private val getProductVideoLinksUseCase: GetProductVideoLinksUseCase,
    private val getImagesUseCase: GetImagesUseCase,
    private val getProductReviewUseCase: GetProductReviewUseCase,
    private val getPersonCreditsUseCase: GetPersonCreditsUseCase
) : BaseViewModel() {

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

    fun loadMovieData(movieId: Int) {
        loadMovieDetails(movieId)
        loadSimilarMovies(movieId)
        loadRecommendedMovies(movieId)
        loadVideoLinks(movieId)
        loadVideoImages(movieId)
        loadReviews(movieId)
        loadCredits(movieId)
    }

    private fun loadMovieDetails(movieId: Int) = subscription.add(
        getProductDetailsViewModel(ProductType.MOVIE, movieId)
            .subscribe({ details ->
                ifLet(details.title, details.overview) { (title, overview) ->
                    _content.value = Pair(title, overview)
                }

                ifNotEmpty(details.voteAverage) { vote ->
                    if (vote > VALUE_ZERO) {
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
    )

    private fun loadSimilarMovies(movieId: Int) =
        subscription.add(getProductAdditionalInformationUseCase(
            ProductType.MOVIE,
            movieId,
            SectionType.SIMILAR
        )
            .filter { it.results.isNotEmpty() }
            .subscribe({
                _similar.value = it.results
            }, Timber::e)
        )

    private fun loadRecommendedMovies(movieId: Int) = subscription.add(
        getProductAdditionalInformationUseCase(
            ProductType.MOVIE,
            movieId,
            SectionType.RECOMMENDATIONS
        )
            .filter { it.results.isNotEmpty() }
            .subscribe({
                _recommended.value = it.results
            }, Timber::e)
    )

    private fun loadVideoLinks(movieId: Int) = subscription.add(
        getProductVideoLinksUseCase(ProductType.MOVIE, movieId)
            .filter { it.results.isNotEmpty() }
            .subscribe({
                _videoLinks.value = it.results
            }, Timber::e)
    )

    private fun loadVideoImages(movieId: Int) = subscription.add(
        getImagesUseCase(ProductType.MOVIE, movieId)
            .filter { it.backdrops.isNotEmpty() }
            .subscribe({
                _backdrops.value = it.backdrops
            }, Timber::e)
    )

    private fun loadReviews(movieId: Int) = subscription.add(
        getProductReviewUseCase(ProductType.MOVIE, movieId)
            .filter { it.result.isNotEmpty() }
            .subscribe({
                _reviews.value = it.result
            }, Timber::e)
    )

    private fun loadCredits(movieId: Int) = subscription.add(
        getPersonCreditsUseCase(ProductType.MOVIE, movieId)
            .subscribe({ response ->
                ifNotEmpty(response.cast) {
                    _cast.value = it.filter { it.profilePath.isNotBlank() }.distinctBy { it.id }
                }

                ifNotEmpty(response.crew) {
                    _crew.value = it.filter { it.profilePath.isNotBlank() }.distinctBy { it.id }
                }
            }, Timber::e)
    )
}
