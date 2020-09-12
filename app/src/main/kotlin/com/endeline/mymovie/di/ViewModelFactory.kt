package com.endeline.mymovie.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.endeline.domain.usecase.*
import com.endeline.mymovie.di.components.DaggerUseCaseComponent
import javax.inject.Inject

class ViewModelFactory {

    class SplashScreenViewModelFactory : ViewModelProvider.Factory {

        @Inject
        lateinit var loadAllDataUseCase: LoadAllDataUseCase

        init {
            DaggerUseCaseComponent.builder().build().inject(this)
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

        init {
            DaggerUseCaseComponent.builder().build().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(
                GetMovieDetailsUseCase::class.java,
                GetSimilarMovieUseCase::class.java,
                GetRecommendedMovieUseCase::class.java,
                GetVideoLinksUseCase::class.java
            ).newInstance(
                getMovieDetailsViewModel,
                getSimilarMovieUseCase,
                getRecommendedMovieUseCase,
                getVideoLinksUseCase
            )
        }
    }

    class NowPlayingViewModelFactory : ViewModelProvider.Factory {

        @Inject
        lateinit var getNowPlayingUseCase: GetNowPlayingUseCase

        init {
            DaggerUseCaseComponent.builder().build().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetNowPlayingUseCase::class.java)
                .newInstance(getNowPlayingUseCase)
        }
    }

    class PopularViewModelFactory : ViewModelProvider.Factory {

        @Inject
        lateinit var getPopularUseCase: GetPopularUseCase

        init {
            DaggerUseCaseComponent.builder().build().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetPopularUseCase::class.java)
                .newInstance(getPopularUseCase)
        }
    }

    class SearchViewModelFactory : ViewModelProvider.Factory {

        @Inject
        lateinit var searchAllUseCase: SearchAllUseCase

        init {
            DaggerUseCaseComponent.builder().build().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(SearchAllUseCase::class.java)
                .newInstance(searchAllUseCase)
        }
    }

    class TopRatedViewModelFactory : ViewModelProvider.Factory {

        @Inject
        lateinit var getTopRatedUseCase: GetTopRatedUseCase

        init {
            DaggerUseCaseComponent.builder().build().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetTopRatedUseCase::class.java)
                .newInstance(getTopRatedUseCase)
        }
    }

    class UpcomingViewModel : ViewModelProvider.Factory {

        @Inject
        lateinit var getUpcomingUseCase: GetUpcomingUseCase

        init {
            DaggerUseCaseComponent.builder().build().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetUpcomingUseCase::class.java)
                .newInstance(getUpcomingUseCase)
        }
    }

    class MainActivityViewModel : ViewModelProvider.Factory {

        @Inject
        lateinit var initializeUserServiceUseCase: InitializeUserServiceUseCase

        init {
            DaggerUseCaseComponent.builder().build().inject(this)
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
            DaggerUseCaseComponent.builder().build().inject(this)
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
            DaggerUseCaseComponent.builder().build().inject(this)
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(
                CheckExistLoginUseCase::class.java,
                RegisterUseCase::class.java
            ).newInstance(checkExistLoginUseCase, registerUseCase)
        }
    }
}
