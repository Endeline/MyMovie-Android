package com.endeline.movielibrary.di.modules

import com.endeline.movielibrary.ui.gui.collection.SectionFragment
import com.endeline.movielibrary.ui.gui.details.movie.MovieDetailsFragment
import com.endeline.movielibrary.ui.gui.details.tv.TvDetailsFragment
import com.endeline.movielibrary.ui.gui.home.HomeFragment
import com.endeline.movielibrary.ui.gui.image.PreviewImageFragment
import com.endeline.movielibrary.ui.gui.person.PersonFragment
import com.endeline.movielibrary.ui.gui.review.ReviewFragment
import com.endeline.movielibrary.ui.gui.search.SearchFragment
import com.endeline.movielibrary.ui.gui.user.UserFragment
import com.endeline.movielibrary.ui.gui.user.forgotpassword.ForgotPasswordFragment
import com.endeline.movielibrary.ui.gui.user.register.RegisterFragment
import com.endeline.movielibrary.ui.gui.video.VideoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSectionFragment(): SectionFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailsFragment(): MovieDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeTvDetailsFragment(): TvDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributePreviewImageFragment(): PreviewImageFragment

    @ContributesAndroidInjector
    abstract fun contributePersonFragment(): PersonFragment

    @ContributesAndroidInjector
    abstract fun contributeReviewFragment(): ReviewFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun contributeForgotPasswordFragment(): ForgotPasswordFragment

    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun contributeUserFragment(): UserFragment

    @ContributesAndroidInjector
    abstract fun contributeVideoFragment(): VideoFragment
}