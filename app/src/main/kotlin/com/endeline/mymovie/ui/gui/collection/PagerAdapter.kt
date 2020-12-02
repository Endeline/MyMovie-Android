package com.endeline.mymovie.ui.gui.collection

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.endeline.common.ProductType
import com.endeline.common.SectionType
import com.endeline.mymovie.R
import java.lang.RuntimeException
import java.lang.UnsupportedOperationException

class PagerAdapter(fragment: Fragment, private val collectionType: ProductType) :
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
        ProductType.MOVIE -> movieTitles.size
        ProductType.TV -> tvTitles.size
        else -> throw RuntimeException("Unsupported type")
    }

    override fun createFragment(position: Int): Fragment {
        val collectionTypeName = when (collectionType) {
            ProductType.MOVIE -> ProductType.MOVIE.name
            ProductType.TV -> ProductType.TV.name
            else -> throw RuntimeException("Unsupported type")
        }

        return when (position) {
            0 -> SectionFragment().apply {
                arguments = Bundle().apply {
                    putString(SectionFragment.COLLECTION_TYPE, collectionTypeName)
                    when (collectionType) {
                        ProductType.MOVIE -> putString(
                            SectionFragment.SECTION_TYPE,
                            SectionType.POPULAR.name
                        )
                        ProductType.TV -> putString(
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
                        ProductType.MOVIE -> putString(
                            SectionFragment.SECTION_TYPE,
                            SectionType.TOP_RATED.name
                        )
                        ProductType.TV -> putString(
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
                        ProductType.MOVIE -> putString(
                            SectionFragment.SECTION_TYPE,
                            SectionType.NOW_PLAYING.name
                        )
                        ProductType.TV -> putString(
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
                        ProductType.MOVIE -> putString(
                            SectionFragment.SECTION_TYPE,
                            SectionType.UPCOMING.name
                        )
                        ProductType.TV -> putString(
                            SectionFragment.SECTION_TYPE,
                            SectionType.ON_THE_AIR.name
                        )
                    }
                }
            }
            else -> throw UnsupportedOperationException()
        }
    }

    fun getNamePosition(position: Int) = when (collectionType) {
        ProductType.MOVIE -> movieTitles[position]
        ProductType.TV -> tvTitles[position]
        else -> throw RuntimeException("Unsupported type")
    }
}
