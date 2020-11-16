package com.endeline.mymovie.ui.collection

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.endeline.domain.ProductType
import com.endeline.mymovie.R
import java.lang.UnsupportedOperationException

class PagerAdapter(fragment: Fragment, private val collectionType: CollectionType) :
    FragmentStateAdapter(fragment) {

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

    override fun createFragment(position: Int): Fragment {
        val collectionTypeName = when (collectionType) {
            CollectionType.MOVIES -> ProductType.movie.name
            CollectionType.TV -> ProductType.tv.name
        }

        return when (position) {
            0 -> SectionFragment().apply {
                arguments = Bundle().apply {
                    putString(SectionFragment.COLLECTION_TYPE, collectionTypeName)
                    when (collectionType) {
                        CollectionType.MOVIES -> putString(
                            SectionFragment.SECTION_TYPE,
                            SectionType.POPULAR.name
                        )
                        CollectionType.TV -> putString(
                            SectionFragment.SECTION_TYPE,
                            SectionType.POPULAR.name
                        )
                    }
                }
            }
            1 -> SectionFragment().apply {
                arguments = Bundle().apply {
                    putString(SectionFragment.COLLECTION_TYPE, collectionTypeName)
                    when (collectionType) {
                        CollectionType.MOVIES -> putString(
                            SectionFragment.SECTION_TYPE,
                            SectionType.TOP_RATED.name
                        )
                        CollectionType.TV -> putString(
                            SectionFragment.SECTION_TYPE,
                            SectionType.TOP_RATED.name
                        )
                    }
                }
            }
            2 -> SectionFragment().apply {
                arguments = Bundle().apply {
                    putString(SectionFragment.COLLECTION_TYPE, collectionTypeName)
                    when (collectionType) {
                        CollectionType.MOVIES -> putString(
                            SectionFragment.SECTION_TYPE,
                            SectionType.NOW_PLAYING.name
                        )
                        CollectionType.TV -> putString(
                            SectionFragment.SECTION_TYPE,
                            SectionType.AIRING_TODAY.name
                        )
                    }
                }
            }
            3 -> SectionFragment().apply {
                arguments = Bundle().apply {
                    putString(SectionFragment.COLLECTION_TYPE, collectionTypeName)
                    when (collectionType) {
                        CollectionType.MOVIES -> putString(
                            SectionFragment.SECTION_TYPE,
                            SectionType.UPCOMING.name
                        )
                        CollectionType.TV -> putString(
                            SectionFragment.SECTION_TYPE,
                            SectionType.THE_AIR.name
                        )
                    }
                }
            }
            else -> throw UnsupportedOperationException()
        }
    }

    fun getNamePosition(position: Int) = when (collectionType) {
        CollectionType.MOVIES -> movieTitles[position]
        CollectionType.TV -> tvTitles[position]
    }
}
