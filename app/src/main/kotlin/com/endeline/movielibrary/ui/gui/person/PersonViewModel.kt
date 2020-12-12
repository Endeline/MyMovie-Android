package com.endeline.movielibrary.ui.gui.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.endeline.common.types.ProductType
import com.endeline.common.types.SectionType
import com.endeline.domain.uimodels.ImagesUiModel.ImageUiModel
import com.endeline.domain.uimodels.PersonUiModel
import com.endeline.domain.uimodels.ProductUiModel
import com.endeline.domain.usecase.GetImagesUseCase
import com.endeline.domain.usecase.GetPersonDetailsUseCase
import com.endeline.domain.usecase.GetProductCreditsUseCase
import com.endeline.movielibrary.Constants.Collections.MINIMUM_COLLECTION_SIZE
import com.endeline.movielibrary.ui.gui.base.BaseViewModel
import timber.log.Timber

class PersonViewModel(
    val getPersonDetailsUseCase: GetPersonDetailsUseCase,
    val getImagesUseCase: GetImagesUseCase,
    val getProductCreditsUseCase: GetProductCreditsUseCase
) : BaseViewModel() {

    private val _personDetails = MutableLiveData<PersonUiModel>()

    val personDetails: LiveData<PersonUiModel>
        get() = _personDetails

    private val _images = MutableLiveData<List<ImageUiModel>>()

    val images: LiveData<List<ImageUiModel>>
        get() = _images

    private val _movieCast = MutableLiveData<List<ProductUiModel>>()

    val movieCast: LiveData<List<ProductUiModel>>
        get() = _movieCast

    private val _tvCast = MutableLiveData<List<ProductUiModel>>()

    val tvCast: LiveData<List<ProductUiModel>>
        get() = _tvCast

    fun load(personId: Int) {
        loadGlobalData(personId)
        loadImages(personId)
        loadMovieCredits(personId)
        loadTvCredits(personId)
    }

    private fun loadGlobalData(personId: Int) = subscription.add(
        getPersonDetailsUseCase(personId)
            .subscribe({
                _personDetails.value = it
            }, Timber::e)
    )

    private fun loadImages(personId: Int) = subscription.add(
        getImagesUseCase(ProductType.PERSON, personId)
            .filter { it.profiles.size > MINIMUM_COLLECTION_SIZE }
            .subscribe({
                _images.value = it.profiles
            }, Timber::e)
    )

    private fun loadMovieCredits(personId: Int) = subscription.add(
        getProductCreditsUseCase(ProductType.PERSON, personId, SectionType.MOVIE_CREDITS)
            .filter { it.cast.isNotEmpty() }
            .subscribe({
                _movieCast.value = it.cast
            }, Timber::e)
    )

    private fun loadTvCredits(personId: Int) = subscription.add(
        getProductCreditsUseCase(ProductType.PERSON, personId, SectionType.TV_CREDITS)
            .filter { it.cast.isNotEmpty() }
            .subscribe({
                _tvCast.value = it.cast
            }, Timber::e)
    )
}
