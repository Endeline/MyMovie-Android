package com.endeline.mymovie.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.endeline.mymovie.R
import com.endeline.mymovie.ui.section.MovieSectionFragment
import com.endeline.mymovie.ui.section.SectionType
import java.lang.UnsupportedOperationException

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val titles = listOf(
        fragment.getString(R.string.popular_title),
        fragment.getString(R.string.top_rated_title),
        fragment.getString(R.string.now_playing_title),
        fragment.getString(R.string.upcoming_title)
    )

    override fun getItemCount() = titles.size

    override fun createFragment(position: Int) = when (position) {
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

    fun getNamePosition(position: Int) = titles[position]
}