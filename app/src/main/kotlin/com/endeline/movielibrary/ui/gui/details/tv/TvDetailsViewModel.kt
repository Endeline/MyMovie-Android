package com.endeline.movielibrary.ui.gui.details.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.endeline.common.types.ProductType
import com.endeline.domain.uimodels.ImagesUiModel.ImageUiModel
import com.endeline.domain.uimodels.ProductDetailsUiModel.SeasonUiModel
import com.endeline.domain.uimodels.ProductDetailsUiModel
import com.endeline.domain.usecase.GetImagesUseCase
import com.endeline.domain.usecase.GetProductDetailsUseCase
import com.endeline.movielibrary.Constants.Values.VALUE_ZERO
import com.endeline.movielibrary.extensions.ifNotEmpty
import com.endeline.movielibrary.ui.gui.base.BaseViewModel
import timber.log.Timber

class TvDetailsViewModel(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val getImagesUseCase: GetImagesUseCase
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

    fun loadData(tvId: Int) {
        loadTvDetails(tvId)
        loadImages(tvId)
        //load videos
        //load load cast
        //load similar
        //load recommended
        //episode details in other fragment ??

        //add network to product details model ?
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

                if (details.voteAverage > VALUE_ZERO) {
                    _voteAverage.value = details.voteAverage
                }

                if (details.numberOfSeasons > VALUE_ZERO) {
                    _seasonCount.value = details.numberOfSeasons
                }

                if (details.numberOfEpisodes > VALUE_ZERO) {
                    _episodeCount.value = details.numberOfEpisodes
                }

                //below check to default
                Timber.d("Wazne nextEpisodeToAir ${details.nextEpisodeToAir}")
                Timber.d("Wazne lastEpisodeToAir ${details.lastEpisodeToAir}")

                //before cast
                Timber.d("Wazne createdBy ${details.createdBy}")

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
}
