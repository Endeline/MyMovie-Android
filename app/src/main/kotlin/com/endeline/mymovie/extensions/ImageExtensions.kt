package com.endeline.mymovie.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

private const val landscapeUrl = "https://image.tmdb.org/t/p/w500"
private const val posterUrl = "https://image.tmdb.org/t/p/w600_and_h900_bestv2"

fun ImageView.loadLandscapeImage(url: String) =
        Glide.with(context)
            .load("$landscapeUrl$url")
            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
            .error(android.R.drawable.stat_notify_error)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)

fun ImageView.loadPosterImage(url: String) =
    Glide.with(context)
        .load("$posterUrl$url")
        .placeholder(android.R.drawable.progress_indeterminate_horizontal)
        .error(android.R.drawable.stat_notify_error)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
