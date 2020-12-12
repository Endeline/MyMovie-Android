package com.endeline.movielibrary.ui.common.carousel

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.endeline.movielibrary.extensions.runOnUiThread
import com.endeline.movielibrary.extensions.smoothSnapToPosition
import com.endeline.movielibrary.ui.Constants
import java.util.*

class RecyclerViewAutoScroll {

    private inner class AutoScrollTask : TimerTask() {
        override fun run() {
            scrollToNextBanner()
        }
    }

    private var recycler: RecyclerView? = null
    private var itemCount: Int? = null
    private var timer: Timer? = null

    fun setup(recycler: RecyclerView, itemCount: Int) {
        this.recycler = recycler
        this.itemCount = itemCount
    }

    fun startScrolling() {
        timer = Timer()
        timer?.scheduleAtFixedRate(
            AutoScrollTask(),
            Constants.ScrollingTime.AUTO_SCROLLING_DELAY_TIME,
            Constants.ScrollingTime.AUTO_SCROLLING_PERIOD_TIME
        )
    }

    fun stopScrolling() {
        timer?.cancel()
        timer = null
    }

    private fun scrollToNextBanner() {
        val layoutManager = (recycler?.layoutManager as LinearLayoutManager)
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (firstVisibleItemPosition == RecyclerView.NO_POSITION) {
            return
        }

        if (itemCount == 1) {
            stopScrolling()
            return
        }

        val nextPosition = firstVisibleItemPosition + 1

        if (nextPosition == itemCount) {
            recycler?.runOnUiThread {
                layoutManager.scrollToPositionWithOffset(0, 0)
            }
        } else {
            recycler?.smoothSnapToPosition(nextPosition)
        }
    }

    companion object {
        const val MINIMUM_BACKDROP_SIZE = 1
    }
}
