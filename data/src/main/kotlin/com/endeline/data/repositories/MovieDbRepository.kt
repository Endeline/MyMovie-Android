package com.endeline.data.repositories

import com.endeline.data.BuildConfig
import com.endeline.data.connectors.MovieDbConnector
import com.endeline.data.models.MovieCollection
import com.endeline.data.models.MovieLatest
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
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

    private lateinit var latestCache: MovieLatest
    private lateinit var nowPlayingCache: MovieCollection
    private lateinit var popularCache: MovieCollection
    private lateinit var topRatedCache: MovieCollection
    private lateinit var upcomingCache: MovieCollection

    fun getLatest() =
        if (::latestCache.isInitialized) {
            Observable.just(latestCache)
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

    fun setCacheNowPlaying(cached: MovieCollection) {
        nowPlayingCache = cached
    }

    fun setCachedLatest(cached: MovieLatest) {
        latestCache = cached
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

}
