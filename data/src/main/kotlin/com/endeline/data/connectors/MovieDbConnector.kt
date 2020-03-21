package com.endeline.data.connectors

import com.endeline.data.BuildConfig
import com.endeline.data.pojos.MovieCollectionPojo
import com.endeline.data.pojos.MovieLatestPojo
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieDbConnector {

    @GET("${BuildConfig.API_VERSION}/movie/latest")
    fun getLatest(): Observable<MovieLatestPojo>

    @GET("${BuildConfig.API_VERSION}/movie/now_playing")
    fun getNowPlaying(): Observable<MovieCollectionPojo>

    @GET("${BuildConfig.API_VERSION}/movie/popular")
    fun getPopular(): Observable<MovieCollectionPojo>

    @GET("${BuildConfig.API_VERSION}/movie/top_rated")
    fun getTopRated(): Observable<MovieCollectionPojo>

    @GET("${BuildConfig.API_VERSION}/movie/upcoming")
    fun getUpcoming(): Observable<MovieCollectionPojo>

}
