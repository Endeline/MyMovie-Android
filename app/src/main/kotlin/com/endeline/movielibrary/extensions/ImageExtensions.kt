package com.endeline.movielibrary.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

private const val BACKDROP_URL = "https://image.tmdb.org/t/p/w500"
private const val POSTER_URL = "https://image.tmdb.org/t/p/w600_and_h900_bestv2"
private const val HTTPS_URL = "https"
private const val SLASH = "/"

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
                    url.subSequence(1, url.length -1)
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
