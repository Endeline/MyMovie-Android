package com.endeline.data.di.modules

import com.endeline.data.BuildConfig
import com.endeline.data.Constants.Api.API_KEY_PARAM
import com.endeline.data.Constants.Api.TIMEOUT_SECOND
import com.endeline.data.repository.ProductRepository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class RetrofitModule {

    @Provides
    fun provideRetrofitService(): ProductRepository = repository

    companion object {
        private val repository = Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIE_DB_BASE_URL)
            .client(OkHttpClient.Builder()
                .readTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS)
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
                    HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) {
                            HttpLoggingInterceptor.Level.BODY
                        } else {
                            HttpLoggingInterceptor.Level.NONE
                        }
                    }
                )
                .build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ProductRepository::class.java)
    }
}
