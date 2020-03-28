package com.endeline.data.connectors

import com.endeline.data.BuildConfig
import com.endeline.data.models.MovieCollection
import com.endeline.data.models.MovieDetails
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDbConnector {

    @GET("${BuildConfig.API_VERSION}/movie/latest")
    fun getLatest(): Observable<MovieDetails>

    @GET("${BuildConfig.API_VERSION}/movie/now_playing")
    fun getNowPlaying(): Observable<MovieCollection>

    @GET("${BuildConfig.API_VERSION}/movie/popular")
    fun getPopular(): Observable<MovieCollection>

    @GET("${BuildConfig.API_VERSION}/movie/top_rated")
    fun getTopRated(): Observable<MovieCollection>

    @GET("${BuildConfig.API_VERSION}/movie/upcoming")
    fun getUpcoming(): Observable<MovieCollection>

    @GET("${BuildConfig.API_VERSION}/movie/{id}")
    fun getMovieDetails(@Path("id") id: Int): Observable<MovieDetails>

}
