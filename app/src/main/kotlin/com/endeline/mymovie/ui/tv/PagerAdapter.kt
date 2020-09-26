package com.endeline.mymovie.ui.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.endeline.mymovie.R
import com.endeline.mymovie.ui.section.SectionType
import com.endeline.mymovie.ui.section.TvSectionFragment
import java.lang.UnsupportedOperationException

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val titles = listOf(
        fragment.getString(R.string.popular_title),
        fragment.getString(R.string.top_rated_title),
        fragment.getString(R.string.airing_today_title),
        fragment.getString(R.string.the_air_title)
    )

    override fun getItemCount() = titles.size

    override fun createFragment(position: Int) = when (position) {
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

    fun getNamePosition(position: Int) = titles[position]
}