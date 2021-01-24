package com.endeline.movielibrary.ui.gui.details.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.endeline.movielibrary.common.types.ProductType
import com.endeline.movielibrary.common.types.SectionType
import com.endeline.movielibrary.domain.uimodels.ImagesUiModel.ImageUiModel
import com.endeline.movielibrary.domain.uimodels.PersonUiModel
import com.endeline.movielibrary.domain.uimodels.ProductDetailsUiModel.SeasonUiModel
import com.endeline.movielibrary.domain.uimodels.ProductDetailsUiModel
import com.endeline.movielibrary.domain.uimodels.ProductUiModel
import com.endeline.movielibrary.domain.uimodels.ReviewsUiModel
import com.endeline.movielibrary.domain.uimodels.VideoLinkCollectionUiModel.VideoLinkDetailsUiModel
import com.endeline.movielibrary.Constants.Values.VALUE_ZERO
import com.endeline.movielibrary.domain.usecase.*
import com.endeline.movielibrary.ui.extensions.ifNotEmpty
import com.endeline.movielibrary.ui.gui.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class TvDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val getImagesUseCase: GetImagesUseCase,
    private val getProductVideoLinksUseCase: GetProductVideoLinksUseCase,
    private val getPersonCreditsUseCase: GetPersonCreditsUseCase,
    private val getProductAdditionalInformationUseCase: GetProductAdditionalInformationUseCase,
    private val getProductReviewUseCase: GetProductReviewUseCase
) : BaseViewModel() {

    private val _details = MutableLiveData<ProductDetailsUiModel>()

    val details: LiveData<ProductDetailsUiModel>
        get() = _details

    private val _backdrops = MutableLiveData<List<ImageUiModel>>()

    val backdrops: LiveData<List<ImageUiModel>>
        get() = _backdrops

    private val _title = MutableLiveData<String>()

    val title: LiveData<String>
        get() = _title

    private val _tagline = MutableLiveData<String>()

    val tagline: LiveData<String>
        get() = _tagline

    private val _voteAverage = MutableLiveData<Double>()

    val voteAverage: LiveData<Double>
        get() = _voteAverage

    private val _popularity = MutableLiveData<Double>()

    val popularity: LiveData<Double>
        get() = _popularity

    private val _status = MutableLiveData<String>()

    val status: LiveData<String>
        get() = _status

    private val _firstAirDate = MutableLiveData<String>()

    val firstAirDate: LiveData<String>
        get() = _firstAirDate

    private val _lastAirDate = MutableLiveData<String>()

    val lastAirDate: LiveData<String>
        get() = _lastAirDate

    private val _seasonCount = MutableLiveData<Int>()

    val seasonCount: LiveData<Int>
        get() = _seasonCount

    private val _episodeCount = MutableLiveData<Int>()

    val episodeCount: LiveData<Int>
        get() = _episodeCount

    private val _description = MutableLiveData<String>()

    val description: LiveData<String>
        get() = _description

    private val _seasons = MutableLiveData<List<SeasonUiModel>>()

    val seasons: LiveData<List<SeasonUiModel>>
        get() = _seasons

    private val _videoLinks = MutableLiveData<List<VideoLinkDetailsUiModel>>()

    val videoLinks: LiveData<List<VideoLinkDetailsUiModel>>
        get() = _videoLinks

    private val _cast = MutableLiveData<List<PersonUiModel>>()

    val cast: LiveData<List<PersonUiModel>>
        get() = _cast

    private val _crew = MutableLiveData<List<PersonUiModel>>()

    val crew: LiveData<List<PersonUiModel>>
        get() = _crew

    private val _similar = MutableLiveData<List<ProductUiModel>>()

    val similar: LiveData<List<ProductUiModel>>
        get() = _similar

    private val _recommended = MutableLiveData<List<ProductUiModel>>()

    val recommended: LiveData<List<ProductUiModel>>
        get() = _recommended

    private val _reviews = MutableLiveData<List<ReviewsUiModel.ReviewUiModel>>()

    val reviews: LiveData<List<ReviewsUiModel.ReviewUiModel>>
        get() = _reviews

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

    fun loadData(tvId: Int) {
        loadTvDetails(tvId)
        loadImages(tvId)
        loadVideo(tvId)
        loadSimilarMovies(tvId)
        loadRecommendedMovies(tvId)
        loadCredits(tvId)
        loadReviews(tvId)
    }

    private fun loadTvDetails(id: Int) = subscription.add(
        getProductDetailsUseCase(ProductType.TV, id)
            .subscribe({ details ->
                _details.value = details

                ifNotEmpty(details.name) { title ->
                    _title.value = title
                }

                ifNotEmpty(details.tagline) { tagline ->
                    _tagline.value = tagline
                }

                ifNotEmpty(details.popularity) { popularity ->
                    _popularity.value = popularity
                }

                ifNotEmpty(details.status) { status ->
                    _status.value = status
                }

                ifNotEmpty(details.firstAirDate) { date ->
                    _firstAirDate.value = date
                }

                ifNotEmpty(details.lastAirDate) { date ->
                    _lastAirDate.value = date
                }

                ifNotEmpty(details.overview) { description ->
                    _description.value = description
                }

                ifNotEmpty(details.seasons) { seasons ->
                    _seasons.value = seasons
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

                if (details.voteAverage > VALUE_ZERO) {
                    _voteAverage.value = details.voteAverage
                }

                if (details.numberOfSeasons > VALUE_ZERO) {
                    _seasonCount.value = details.numberOfSeasons
                }

                if (details.numberOfEpisodes > VALUE_ZERO) {
                    _episodeCount.value = details.numberOfEpisodes
                }


                //on last like in movie
                Timber.d("Wazne genres ${details.genres}")
                Timber.d("Wazne spokenLanguages ${details.spokenLanguages}")
                Timber.d("Wazne productionCountries ${details.productionCountries}")
                Timber.d("Wazne productionCompanies ${details.productionCompanies}")
            }, Timber::e)
    )

    private fun loadImages(id: Int) = subscription.add(getImagesUseCase(ProductType.TV, id)
        .filter { it.backdrops.isNotEmpty() }
        .subscribe({
            _backdrops.value = it.backdrops
        }, Timber::e)
    )

    private fun loadVideo(id: Int) =
        subscription.add(
            getProductVideoLinksUseCase(ProductType.TV, id)
                .filter { it.results.isNotEmpty() }
                .subscribe({
                    _videoLinks.value = it.results
                }, Timber::e)
        )

    private fun loadSimilarMovies(it: Int) =
        subscription.add(getProductAdditionalInformationUseCase(
            ProductType.TV,
            it,
            SectionType.SIMILAR
        )
            .filter { it.results.isNotEmpty() }
            .subscribe({
                _similar.value = it.results
            }, Timber::e)
        )

    private fun loadRecommendedMovies(id: Int) = subscription.add(
        getProductAdditionalInformationUseCase(
            ProductType.TV,
            id,
            SectionType.RECOMMENDATIONS
        )
            .filter { it.results.isNotEmpty() }
            .subscribe({
                _recommended.value = it.results
            }, Timber::e)
    )

    private fun loadCredits(id: Int) = subscription.add(
        getPersonCreditsUseCase(ProductType.TV, id)
            .subscribe({ response ->
                ifNotEmpty(response.cast) {
                    _cast.value = it.filter { it.profilePath.isNotBlank() }.distinctBy { it.id }
                }

                ifNotEmpty(response.crew) {
                    _crew.value = it.filter { it.profilePath.isNotBlank() }.distinctBy { it.id }
                }
            }, Timber::e)
    )

    private fun loadReviews(id: Int) = subscription.add(
        getProductReviewUseCase(ProductType.TV, id)
            .filter { it.result.isNotEmpty() }
            .subscribe({
                _reviews.value = it.result
            }, Timber::e)
    )
}
