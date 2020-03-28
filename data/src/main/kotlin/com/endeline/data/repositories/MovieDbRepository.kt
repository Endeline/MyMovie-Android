package com.endeline.data.repositories

import com.endeline.data.BuildConfig
import com.endeline.data.connectors.MovieDbConnector
import com.endeline.data.models.MovieCollection
import com.endeline.data.models.MovieDetails
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
class MovieDbRepository {
    companion object {
        private const val API_KEY_PARAM = "api_key"

        private val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIE_DB_BASE_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor {
                    var request = it.request()

                    val url = request.url()
                        .newBuilder()
                        .addQueryParameter(API_KEY_PARAM, BuildConfig.MOVIE_API_KEY)
                        .build()

                    request = request.newBuilder()
                        .url(url)
                        .build()

                    it.proceed(request)
                }
                .addInterceptor(
                    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
                )
                .build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        private val service = retrofit.create(MovieDbConnector::class.java)
    }

    private lateinit var movieDetailsCache: MovieDetails
    private lateinit var nowPlayingCache: MovieCollection
    private lateinit var popularCache: MovieCollection
    private lateinit var topRatedCache: MovieCollection
    private lateinit var upcomingCache: MovieCollection
    private lateinit var movieDetailsListCached: MutableList<MovieDetails>

    fun getLatest() =
        if (::movieDetailsCache.isInitialized) {
            Observable.just(movieDetailsCache)
        } else {
            service.getLatest()
        }

    fun getNowPlaying() =
        if (::nowPlayingCache.isInitialized) {
            Observable.just(nowPlayingCache)
        } else {
            service.getNowPlaying()
        }

    fun getPopular() =
        if (::popularCache.isInitialized) {
            Observable.just(popularCache)
        } else {
            service.getPopular()
        }

    fun getTopRated() =
        if (::topRatedCache.isInitialized) {
            Observable.just(topRatedCache)
        } else {
            service.getTopRated()
        }

    fun getUpcoming() =
        if (::upcomingCache.isInitialized) {
            Observable.just(upcomingCache)
        } else {
            service.getUpcoming()
        }

    fun getMovieDetails(id: Int) =
        if (::movieDetailsListCached.isInitialized) {
            val movie = movieDetailsListCached.filter { it.id == id }

            if (movie.isEmpty()) {
                service.getMovieDetails(id)
            } else {
                Observable.just(movie[0])
            }
        } else {
            service.getMovieDetails(id)
        }

    fun setCacheNowPlaying(cached: MovieCollection) {
        nowPlayingCache = cached
    }

    fun setCachedLatest(cached: MovieDetails) {
        movieDetailsCache = cached
    }

    fun setCachedPopular(cached: MovieCollection) {
        popularCache = cached
    }

    fun setCachedTopRated(cached: MovieCollection) {
        topRatedCache = cached
    }

    fun setCachedUpcoming(cached: MovieCollection) {
        upcomingCache = cached
    }

    fun addCachedMovieDetails(cached: MovieDetails) {
        if (!::movieDetailsListCached.isInitialized) {
            movieDetailsListCached = mutableListOf()
        }

        if (!movieDetailsListCached.contains(cached)) {
            movieDetailsListCached.add(cached)
        }
    }

}
