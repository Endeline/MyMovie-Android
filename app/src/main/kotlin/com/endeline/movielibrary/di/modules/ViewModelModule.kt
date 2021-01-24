package com.endeline.movielibrary.di.modules

import androidx.lifecycle.ViewModel
import com.endeline.movielibrary.di.retention.ViewModelKey
import com.endeline.movielibrary.ui.gui.collection.SectionViewModel
import com.endeline.movielibrary.ui.gui.details.movie.MovieDetailsViewModel
import com.endeline.movielibrary.ui.gui.details.tv.TvDetailsViewModel
import com.endeline.movielibrary.ui.gui.main.MainActivityViewModel
import com.endeline.movielibrary.ui.gui.person.PersonViewModel
import com.endeline.movielibrary.ui.gui.search.SearchViewModel
import com.endeline.movielibrary.ui.gui.splash.SplashViewModel
import com.endeline.movielibrary.ui.gui.user.UserViewModel
import com.endeline.movielibrary.ui.gui.user.forgotpassword.ForgotPasswordViewModel
import com.endeline.movielibrary.ui.gui.user.register.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SectionViewModel::class)
    abstract fun bindSectionViewModel(sectionViewModel: SectionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    abstract fun bindMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvDetailsViewModel::class)
    abstract fun bindTvDetailsViewModel(tvDetailsViewModel: TvDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PersonViewModel::class)
    abstract fun bindPersonViewModel(personViewModel: PersonViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForgotPasswordViewModel::class)
    abstract fun bindForgotPasswordViewModel(forgotPasswordViewModel: ForgotPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(userViewModel: UserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel
}
