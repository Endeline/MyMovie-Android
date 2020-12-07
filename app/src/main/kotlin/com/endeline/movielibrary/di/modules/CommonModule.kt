package com.endeline.movielibrary.di.modules

import com.endeline.movielibrary.ui.common.autoscroll.RecyclerViewAutoScroll
import com.endeline.movielibrary.ui.common.imagecarousel.ImagesCarouselAdapter
import com.endeline.movielibrary.ui.common.reviews.ReviewsAdapter
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
