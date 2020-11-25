package com.endeline.mymovie.di.modules

import com.endeline.mymovie.ui.common.autoscroll.RecyclerViewAutoScroll
import com.endeline.mymovie.ui.common.imagecarousel.ImagesCarouselAdapter
import com.endeline.mymovie.ui.common.reviews.ReviewsAdapter
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
}
