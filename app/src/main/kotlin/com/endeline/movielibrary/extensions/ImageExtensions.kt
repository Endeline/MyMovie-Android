package com.endeline.movielibrary.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.endeline.movielibrary.ui.Constants.Image.BACKDROP_URL
import com.endeline.movielibrary.ui.Constants.Image.HTTPS_URL
import com.endeline.movielibrary.ui.Constants.Image.POSTER_URL
import com.endeline.movielibrary.ui.Constants.Image.SLASH
import com.endeline.movielibrary.ui.Constants.Image.SUB_SEQUENCE_VALUE

fun ImageView.loadBackdropImage(url: String) =
    Glide.with(context)
        .load("$BACKDROP_URL$url")
        .placeholder(android.R.drawable.progress_indeterminate_horizontal)
        .error(android.R.drawable.stat_notify_error)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)

fun ImageView.loadPosterImage(url: String) =
    Glide.with(context)
        .load("$POSTER_URL$url")
        .placeholder(android.R.drawable.progress_indeterminate_horizontal)
        .error(android.R.drawable.stat_notify_error)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)

fun ImageView.loadImage(url: String) =
    Glide.with(context)
        .load(
            if (url.contains(HTTPS_URL)) {
                if (url.first().toString() == SLASH) {
                    url.subSequence(SUB_SEQUENCE_VALUE, url.length - SUB_SEQUENCE_VALUE)
                } else {
                    url
                }
            } else {
                "$POSTER_URL$url"
            }
        )
        .placeholder(android.R.drawable.progress_indeterminate_horizontal)
        .error(android.R.drawable.stat_notify_error)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
