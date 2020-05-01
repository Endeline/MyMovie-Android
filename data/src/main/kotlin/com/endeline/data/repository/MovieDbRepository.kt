package com.endeline.data.repository

import com.endeline.data.BuildConfig
import com.endeline.data.models.MovieCollection
import com.endeline.data.models.MovieDetails
import com.endeline.data.models.SearchAll
import com.endeline.data.models.VideoLinkCollection
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbRepository {

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

    @GET("${BuildConfig.API_VERSION}/movie/{id}/similar")
    fun getSimilarMovies(@Path("id") id: Int): Observable<MovieCollection>

    @GET("${BuildConfig.API_VERSION}/movie/{id}/recommendations")
    fun getRecommendedMovies(@Path("id") id: Int): Observable<MovieCollection>

    @GET("${BuildConfig.API_VERSION}/movie/{id}/videos")
    fun getVideoLinks(@Path("id") id: Int): Observable<VideoLinkCollection>

    @GET("${BuildConfig.API_VERSION}/search/multi")
    fun searchAll(@Query("query") query: String): Observable<SearchAll>

}
