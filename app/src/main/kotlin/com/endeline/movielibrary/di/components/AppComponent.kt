package com.endeline.movielibrary.di.components

import com.endeline.movielibrary.di.modules.CommonModule
import com.endeline.movielibrary.di.modules.ViewModelModule
import com.endeline.movielibrary.ui.gui.collection.SectionFragment
import com.endeline.movielibrary.ui.gui.person.PersonFragment
import com.endeline.movielibrary.ui.gui.details.movie.MovieDetailsFragment
import com.endeline.movielibrary.ui.gui.main.MainActivity
import com.endeline.movielibrary.ui.gui.search.SearchFragment
import com.endeline.movielibrary.ui.gui.splash.SplashScreenActivity
import com.endeline.movielibrary.ui.gui.details.tv.TvDetailsFragment
import com.endeline.movielibrary.ui.gui.user.UserFragment
import com.endeline.movielibrary.ui.gui.user.register.RegisterFragment
import dagger.Component

@Component(modules = [ViewModelModule::class, CommonModule::class])
interface AppComponent {
    fun inject(splashScreenActivity: SplashScreenActivity)
    fun inject(movieDetailsFragment: MovieDetailsFragment)
    fun inject(sectionFragment: SectionFragment)
    fun inject(searchFragment: SearchFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(userFragment: UserFragment)
    fun inject(registerFragment: RegisterFragment)
    fun inject(creditFragment: PersonFragment)
    fun inject(tvDetailsFragment: TvDetailsFragment)
}
