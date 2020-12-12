package com.endeline.movielibrary.extensions

import android.os.Handler
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.endeline.movielibrary.Constants.Duration.DEFAULT_SPEED
import com.endeline.movielibrary.Constants.Duration.RECYCLER_VIEW_ITEM_DURATION
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator

fun RecyclerView.runOnUiThread(block: () -> Unit) {
    Handler(context.mainLooper).post { block() }
}

fun RecyclerView.smoothSnapToPosition(
    position: Int,
    snapMode: Int = LinearSmoothScroller.SNAP_TO_START,
    speed: Float = DEFAULT_SPEED
) {
    val smoothScroller = object : LinearSmoothScroller(this.context) {
        override fun getVerticalSnapPreference(): Int = snapMode
        override fun getHorizontalSnapPreference(): Int = snapMode
        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float =
            speed / displayMetrics.densityDpi
    }

    smoothScroller.targetPosition = position
    layoutManager?.startSmoothScroll(smoothScroller)
}

fun RecyclerView.setupWithAdapter(adapter: RecyclerView.Adapter<*>) {
    setHasFixedSize(true)
    layoutManager = LinearLayoutManager(
        context,
        LinearLayoutManager.HORIZONTAL,
        false
    )
    this.adapter = adapter
    itemAnimator = SlideInRightAnimator().apply {
        addDuration = RECYCLER_VIEW_ITEM_DURATION
    }
}

fun RecyclerView.setupWithAdapterAndRemoveAnimation(adapter: RecyclerView.Adapter<*>) {
    setHasFixedSize(true)
    layoutManager = LinearLayoutManager(
        context,
        LinearLayoutManager.HORIZONTAL,
        false
    )
    this.adapter = adapter
    itemAnimator = SlideInRightAnimator().apply {
        addDuration = RECYCLER_VIEW_ITEM_DURATION
        removeDuration = RECYCLER_VIEW_ITEM_DURATION
    }
}
