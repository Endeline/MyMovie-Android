package com.endeline.movielibrary.di.modules

import com.endeline.movielibrary.ui.common.carousel.RecyclerViewAutoScroll
import com.endeline.movielibrary.ui.common.carousel.ImagesCarouselAdapter
import com.endeline.movielibrary.ui.common.poster.PosterImageAdapter
import com.endeline.movielibrary.ui.common.reviews.ReviewsAdapter
import com.endeline.movielibrary.ui.common.video.VideoAdapter
import com.endeline.movielibrary.ui.gui.details.tv.SeasonAdapter
import dagger.Module
import dagger.Provides

@Module
class CommonModule {

    @Provides
    fun provideImageCarouselAdapter() = ImagesCarouselAdapter()

    @Provides
    fun provideRecyclerViewAutoScroll() = RecyclerViewAutoScroll()

    @Provides
    fun provideReviewsAdapter() = ReviewsAdapter()

    @Provides
    fun providePosterImageAdapter() = PosterImageAdapter()

    @Provides
    fun provideSeasonAdapter() = SeasonAdapter()

    @Provides
    fun provideVideoLinksAdapter() = VideoAdapter()
}
