package com.endeline.mymovie.di.components

import com.endeline.mymovie.di.modules.CommonModule
import com.endeline.mymovie.di.modules.ViewModelModule
import com.endeline.mymovie.ui.gui.collection.SectionFragment
import com.endeline.mymovie.ui.gui.credit.PersonFragment
import com.endeline.mymovie.ui.gui.details.DetailsFragment
import com.endeline.mymovie.ui.gui.main.MainActivity
import com.endeline.mymovie.ui.gui.search.SearchFragment
import com.endeline.mymovie.ui.gui.splash.SplashScreenActivity
import com.endeline.mymovie.ui.gui.user.UserFragment
import com.endeline.mymovie.ui.gui.user.register.RegisterFragment
import dagger.Component

@Component(modules = [ViewModelModule::class, CommonModule::class])
interface AppComponent {
    fun inject(splashScreenActivity: SplashScreenActivity)
    fun inject(splashScreenActivity: DetailsFragment)
    fun inject(sectionFragment: SectionFragment)
    fun inject(searchFragment: SearchFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(userFragment: UserFragment)
    fun inject(registerFragment: RegisterFragment)
    fun inject(creditFragment: PersonFragment)
}
