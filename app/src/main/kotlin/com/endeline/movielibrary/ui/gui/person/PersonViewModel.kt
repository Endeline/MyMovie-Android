package com.endeline.movielibrary.ui.gui.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.endeline.common.ProductType
import com.endeline.domain.uimodels.ImagesUiModel.ImageUiModel
import com.endeline.domain.uimodels.PersonUiModel
import com.endeline.domain.usecase.GetImagesUseCase
import com.endeline.domain.usecase.GetPersonDetailsUseCase
import com.endeline.movielibrary.ui.gui.base.BaseViewModel
import timber.log.Timber

class PersonViewModel(
    val getPersonDetailsUseCase: GetPersonDetailsUseCase,
    val getImagesUseCase: GetImagesUseCase
) : BaseViewModel() {

    private val _personDetails = MutableLiveData<PersonUiModel>()

    val personDetails: LiveData<PersonUiModel>
        get() = _personDetails

    private val _images = MutableLiveData<List<ImageUiModel>>()

    val images: LiveData<List<ImageUiModel>>
        get() = _images

    fun load(personId: Int) {
        loadGlobalData(personId)
        loadImages(personId)

    }

    private fun loadGlobalData(personId: Int) = subscription.add(
        getPersonDetailsUseCase(personId)
            .subscribe({
                _personDetails.value = it
            }, Timber::e)
    )


    private fun loadImages(personId: Int) = subscription.add(
        getImagesUseCase(ProductType.PERSON, personId)
            .filter { it.profiles.size > 1 }
            .subscribe({
                _images.value = it.profiles
            }, Timber::e)
    )
}
