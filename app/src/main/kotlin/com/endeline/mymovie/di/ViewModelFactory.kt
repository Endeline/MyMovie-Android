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
            DaggerViewModelComponent.builder().build().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(LoadAllDataUseCase::class.java)
                .newInstance(loadAllDataUseCase)
        }
    }

    class DetailsViewModelFactory : ViewModelProvider.Factory {

        @Inject
        lateinit var getMovieDetailsViewModel: GetMovieDetailsUseCase

        @Inject
        lateinit var getSimilarMovieUseCase: GetSimilarMovieUseCase

        @Inject
        lateinit var getRecommendedMovieUseCase: GetRecommendedMovieUseCase

        @Inject
        lateinit var getVideoLinksUseCase: GetVideoLinksUseCase

        @Inject
        lateinit var getProductImagesUseCase: GetProductImagesUseCase

        @Inject
        lateinit var getProductReviewUseCase: GetProductReviewUseCase

        @Inject
        lateinit var getProductCreditsUseCase: GetProductCreditsUseCase

        init {
            DaggerViewModelComponent.builder().build().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(
                GetMovieDetailsUseCase::class.java,
                GetSimilarMovieUseCase::class.java,
                GetRecommendedMovieUseCase::class.java,
                GetVideoLinksUseCase::class.java,
                GetProductImagesUseCase::class.java,
                GetProductReviewUseCase::class.java,
                GetProductCreditsUseCase::class.java
            ).newInstance(
                getMovieDetailsViewModel,
                getSimilarMovieUseCase,
                getRecommendedMovieUseCase,
                getVideoLinksUseCase,
                getProductImagesUseCase,
                getProductReviewUseCase,
                getProductCreditsUseCase
            )
        }
    }

    class SectionViewModelFactory : ViewModelProvider.Factory {

        @Inject
        lateinit var getNowPlayingUseCase: GetNowPlayingUseCase

        @Inject
        lateinit var getPopularUseCase: GetPopularUseCase

        @Inject
        lateinit var getTopRatedUseCase: GetTopRatedUseCase

        @Inject
        lateinit var getUpcomingUseCase: GetUpcomingUseCase

        @Inject
        lateinit var getAiringTodayUseCase: GetAiringTodayUseCase

        @Inject
        lateinit var getTheAirUseUseCase: GetTheAirUseUseCase

        init {
            DaggerViewModelComponent.builder().build().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(
                GetNowPlayingUseCase::class.java,
                GetPopularUseCase::class.java,
                GetTopRatedUseCase::class.java,
                GetUpcomingUseCase::class.java,
                GetAiringTodayUseCase::class.java,
                GetTheAirUseUseCase::class.java
            ).newInstance(
                getNowPlayingUseCase,
                getPopularUseCase,
                getTopRatedUseCase,
                getUpcomingUseCase,
                getAiringTodayUseCase,
                getTheAirUseUseCase
            )
        }
    }

    class SearchViewModelFactory : ViewModelProvider.Factory {

        @Inject
        lateinit var searchAllUseCase: SearchAllUseCase

        init {
            DaggerViewModelComponent.builder().build().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(SearchAllUseCase::class.java)
                .newInstance(searchAllUseCase)
        }
    }

    class MainActivityViewModel : ViewModelProvider.Factory {

        @Inject
        lateinit var initializeUserServiceUseCase: InitializeUserServiceUseCase

        init {
            DaggerViewModelComponent.builder().build().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(
                InitializeUserServiceUseCase::class.java
            ).newInstance(initializeUserServiceUseCase)
        }
    }

    class UserViewModel : ViewModelProvider.Factory {

        @Inject
        lateinit var getUserIsLoggedIn: GetUserIsLoggedInUseCase

        @Inject
        lateinit var checkIsUserInAppUseCase: CheckIsUserInAppUseCase


        init {
            DaggerViewModelComponent.builder().build().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(
                GetUserIsLoggedInUseCase::class.java,
                CheckIsUserInAppUseCase::class.java
            ).newInstance(getUserIsLoggedIn, checkIsUserInAppUseCase)
        }
    }

    class RegisterViewModel : ViewModelProvider.Factory {

        @Inject
        lateinit var checkExistLoginUseCase: CheckExistLoginUseCase

        @Inject
        lateinit var registerUseCase: RegisterUseCase

        init {
            DaggerViewModelComponent.builder().build().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(
                CheckExistLoginUseCase::class.java,
                RegisterUseCase::class.java
            ).newInstance(checkExistLoginUseCase, registerUseCase)
        }
    }
}
