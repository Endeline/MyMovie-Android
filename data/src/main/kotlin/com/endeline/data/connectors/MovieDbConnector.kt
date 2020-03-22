package com.endeline.data.connectors

import com.endeline.data.BuildConfig
import com.endeline.data.models.MovieCollection
import com.endeline.data.models.MovieLatest
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieDbConnector {

    @GET("${BuildConfig.API_VERSION}/movie/latest")
    fun getLatest(): Observable<MovieLatest>

    @GET("${BuildConfig.API_VERSION}/movie/now_playing")
    fun getNowPlaying(): Observable<MovieCollection>

    @GET("${BuildConfig.API_VERSION}/movie/popular")
    fun getPopular(): Observable<MovieCollection>

    @GET("${BuildConfig.API_VERSION}/movie/top_rated")
    fun getTopRated(): Observable<MovieCollection>

    @GET("${BuildConfig.API_VERSION}/movie/upcoming")
    fun getUpcoming(): Observable<MovieCollection>

}
