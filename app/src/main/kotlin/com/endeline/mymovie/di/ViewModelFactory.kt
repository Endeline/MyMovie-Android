package com.endeline.mymovie.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.endeline.domain.usecase.*
import com.endeline.mymovie.di.components.DaggerViewModelComponent
import javax.inject.Inject

class ViewModelFactory {

    class SplashScreenViewModelFactory : ViewModelProvider.Factory {

        @Inject
        lateinit var loadAllDataUseCase: LoadAllDataUseCase

        init {
            DaggerViewModelComponent.create().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            modelClass.getConstructor(LoadAllDataUseCase::class.java)
                .newInstance(loadAllDataUseCase)
    }

    class DetailsViewModelFactory : ViewModelProvider.Factory {

        @Inject
        lateinit var getMovieDetailsViewModel: GetMovieDetailsUseCase

        @Inject
        lateinit var getProductAdditionalInformationUseCase: GetProductAdditionalInformationUseCase

        @Inject
        lateinit var getVideoLinksUseCase: GetVideoLinksUseCase

        @Inject
        lateinit var getProductImagesUseCase: GetProductImagesUseCase

        @Inject
        lateinit var getProductReviewUseCase: GetProductReviewUseCase

        @Inject
        lateinit var getProductCreditsUseCase: GetProductCreditsUseCase

        init {
            DaggerViewModelComponent.create().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            modelClass.getConstructor(
                GetMovieDetailsUseCase::class.java,
                GetProductAdditionalInformationUseCase::class.java,
                GetVideoLinksUseCase::class.java,
                GetProductImagesUseCase::class.java,
                GetProductReviewUseCase::class.java,
                GetProductCreditsUseCase::class.java
            ).newInstance(
                getMovieDetailsViewModel,
                getProductAdditionalInformationUseCase,
                getVideoLinksUseCase,
                getProductImagesUseCase,
                getProductReviewUseCase,
                getProductCreditsUseCase
            )
    }

    class SectionViewModelFactory : ViewModelProvider.Factory {

        @Inject
        lateinit var getProductWithTypes: GetProductsWithTypes

        init {
            DaggerViewModelComponent.create().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            modelClass.getConstructor(GetProductsWithTypes::class.java).newInstance(getProductWithTypes)
    }

    class SearchViewModelFactory : ViewModelProvider.Factory {

        @Inject
        lateinit var searchAllUseCase: SearchAllUseCase

        init {
            DaggerViewModelComponent.create().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            modelClass.getConstructor(SearchAllUseCase::class.java)
                .newInstance(searchAllUseCase)
    }

    class MainActivityViewModelFactory : ViewModelProvider.Factory {

        @Inject
        lateinit var initializeUserServiceUseCase: InitializeUserServiceUseCase

        init {
            DaggerViewModelComponent.create().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            modelClass.getConstructor(
                InitializeUserServiceUseCase::class.java
            ).newInstance(initializeUserServiceUseCase)
    }

    class UserViewModelFactory : ViewModelProvider.Factory {

        @Inject
        lateinit var getUserIsLoggedIn: GetUserIsLoggedInUseCase

        @Inject
        lateinit var checkIsUserInAppUseCase: CheckIsUserInAppUseCase


        init {
            DaggerViewModelComponent.create().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            modelClass.getConstructor(
                GetUserIsLoggedInUseCase::class.java,
                CheckIsUserInAppUseCase::class.java
            ).newInstance(getUserIsLoggedIn, checkIsUserInAppUseCase)
    }

    class RegisterViewModelFactory : ViewModelProvider.Factory {

        @Inject
        lateinit var checkExistLoginUseCase: CheckExistLoginUseCase

        @Inject
        lateinit var registerUseCase: RegisterUseCase

        init {
            DaggerViewModelComponent.create().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            modelClass.getConstructor(
                CheckExistLoginUseCase::class.java,
                RegisterUseCase::class.java
            ).newInstance(checkExistLoginUseCase, registerUseCase)
    }

    class PersonViewModelFactory : ViewModelProvider.Factory {

        @Inject
        lateinit var getPersonDetailsUseCase: GetPersonDetailsUseCase

        init {
            DaggerViewModelComponent.create().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            modelClass.getConstructor(GetPersonDetailsUseCase::class.java)
                .newInstance(getPersonDetailsUseCase)
    }
}
