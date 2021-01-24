package com.endeline.movielibrary.ui.common.carousel

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.endeline.movielibrary.ui.extensions.runOnUiThread
import com.endeline.movielibrary.ui.extensions.smoothSnapToPosition
import com.endeline.movielibrary.Constants.Duration.AUTO_SCROLLING_DELAY_TIME
import com.endeline.movielibrary.Constants.Duration.AUTO_SCROLLING_PERIOD_TIME
import com.endeline.movielibrary.Constants.Values.VALUE_ONE
import com.endeline.movielibrary.Constants.Values.VALUE_ZERO
import java.util.*
import javax.inject.Inject

class RecyclerViewAutoScroll @Inject constructor(){

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
            AUTO_SCROLLING_DELAY_TIME,
            AUTO_SCROLLING_PERIOD_TIME
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

        if (itemCount == VALUE_ONE) {
            stopScrolling()
            return
        }

        val nextPosition = firstVisibleItemPosition + VALUE_ONE

        if (nextPosition == itemCount) {
            recycler?.runOnUiThread {
                layoutManager.scrollToPositionWithOffset(VALUE_ZERO, VALUE_ZERO)
            }
        } else {
            recycler?.smoothSnapToPosition(nextPosition)
        }
    }
}
