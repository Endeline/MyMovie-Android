package com.endeline.mymovie.di.components

import com.endeline.mymovie.MainActivity
import com.endeline.mymovie.di.modules.UseCaseModules
import dagger.Component


@Component(modules = [UseCaseModules::class])
interface AppComponent {

    fun inject(app: MainActivity)

}
