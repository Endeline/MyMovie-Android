package com.endeline.mymovie.ui.collection

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.endeline.mymovie.R
import com.endeline.mymovie.ui.section.MovieSectionFragment
import com.endeline.mymovie.ui.section.SectionType
import com.endeline.mymovie.ui.section.TvSectionFragment
import java.lang.UnsupportedOperationException

class PagerAdapter(fragment: Fragment, private val collectionType: CollectionType) : FragmentStateAdapter(fragment) {

    private val movieTitles = listOf(
        fragment.getString(R.string.popular_title),
        fragment.getString(R.string.top_rated_title),
        fragment.getString(R.string.now_playing_title),
        fragment.getString(R.string.upcoming_title)
    )

    private val tvTitles = listOf(
        fragment.getString(R.string.popular_title),
        fragment.getString(R.string.top_rated_title),
        fragment.getString(R.string.airing_today_title),
        fragment.getString(R.string.the_air_title)
    )

    override fun getItemCount() = when (collectionType) {
        CollectionType.MOVIES -> movieTitles.size
        CollectionType.TV -> tvTitles.size
    }

    override fun createFragment(position: Int) = when (collectionType) {
        CollectionType.MOVIES -> createMovieFragment(position)
        CollectionType.TV -> createTvFragment(position)
    }

    fun getNamePosition(position: Int) = when (collectionType) {
        CollectionType.MOVIES -> movieTitles[position]
        CollectionType.TV -> tvTitles[position]
    }

    private fun createMovieFragment(position: Int) = when (position) {
        0 -> MovieSectionFragment().apply {
            arguments = Bundle().apply {
                putString(MovieSectionFragment.MOVIE_SECTION_TYPE, SectionType.POPULAR.name)
            }
        }
        1 -> MovieSectionFragment().apply {
            arguments = Bundle().apply {
                putString(MovieSectionFragment.MOVIE_SECTION_TYPE, SectionType.TOP_RATED.name)
            }
        }
        2 -> MovieSectionFragment().apply {
            arguments = Bundle().apply {
                putString(MovieSectionFragment.MOVIE_SECTION_TYPE, SectionType.NOW_PLAYING.name)
            }
        }
        3 -> MovieSectionFragment().apply {
            arguments = Bundle().apply {
                putString(MovieSectionFragment.MOVIE_SECTION_TYPE, SectionType.UPCOMING.name)
            }
        }
        else -> throw UnsupportedOperationException()
    }

    private fun createTvFragment(position: Int) = when (position) {
        0 -> TvSectionFragment().apply {
            arguments = Bundle().apply {
                putString(TvSectionFragment.TV_SECTION_TYPE, SectionType.POPULAR.name)
            }
        }
        1 -> TvSectionFragment().apply {
            arguments = Bundle().apply {
                putString(TvSectionFragment.TV_SECTION_TYPE, SectionType.TOP_RATED.name)
            }
        }
        2 -> TvSectionFragment().apply {
            arguments = Bundle().apply {
                putString(TvSectionFragment.TV_SECTION_TYPE, SectionType.AIRING_TODAY.name)
            }
        }
        3 -> TvSectionFragment().apply {
            arguments = Bundle().apply {
                putString(TvSectionFragment.TV_SECTION_TYPE, SectionType.THE_AIR.name)
            }
        }
        else -> throw UnsupportedOperationException()
    }
}
