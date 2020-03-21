package com.endeline.data.repositories

import com.endeline.data.BuildConfig
import com.endeline.data.connectors.MovieDbConnector
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//todo cache or meybe splash screen for this we dont wont ask server every this if user change fragment

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

    fun getLatest() =
        service.getLatest()

    fun getNowPlaying() =
        service.getNowPlaying()

    fun getPopular() =
        service.getPopular()

    fun getTopRated() =
        service.getTopRated()

    fun getUpcoming() =
        service.getUpcoming()
}
