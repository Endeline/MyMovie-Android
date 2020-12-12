package com.endeline.movielibrary.ui.gui.collection

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.endeline.common.ProductType
import com.endeline.common.SectionType
import com.endeline.movielibrary.R
import com.endeline.movielibrary.ui.Constants.Position.FIRST
import com.endeline.movielibrary.ui.Constants.Position.FOURTH
import com.endeline.movielibrary.ui.Constants.Position.SECOND
import com.endeline.movielibrary.ui.Constants.Position.THIRD
import com.endeline.movielibrary.ui.Constants.String.UNSUPPORTED_TYPE
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
        else -> throw RuntimeException(UNSUPPORTED_TYPE)
    }

    override fun createFragment(position: Int): Fragment {
        val collectionTypeName = when (collectionType) {
            ProductType.MOVIE -> ProductType.MOVIE.name
            ProductType.TV -> ProductType.TV.name
            else -> throw RuntimeException(UNSUPPORTED_TYPE)
        }

        return when (position) {
            FIRST -> SectionFragment().apply {
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
                        else -> throw RuntimeException(UNSUPPORTED_TYPE)
                    }
                }
            }
            SECOND -> SectionFragment().apply {
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
                        else -> throw RuntimeException(UNSUPPORTED_TYPE)
                    }
                }
            }
            THIRD -> SectionFragment().apply {
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
                        else -> throw RuntimeException(UNSUPPORTED_TYPE)
                    }
                }
            }
            FOURTH -> SectionFragment().apply {
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
                        else -> throw RuntimeException(UNSUPPORTED_TYPE)
                    }
                }
            }
            else -> throw UnsupportedOperationException()
        }
    }

    fun getNamePosition(position: Int) = when (collectionType) {
        ProductType.MOVIE -> movieTitles[position]
        ProductType.TV -> tvTitles[position]
        else -> throw RuntimeException(UNSUPPORTED_TYPE)
    }
}
