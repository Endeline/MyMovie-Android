package com.endeline.data.connectors

import com.endeline.data.pojos.MovieCollectionPojo
import com.endeline.data.pojos.MovieLatestPojo
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieDbConnector {

    @GET("3/movie/latest")
    fun getLatest(): Observable<MovieLatestPojo>

    @GET("/movie/now_playing")
    fun getNowPlaying(): Observable<MovieCollectionPojo>

    @GET("/movie/popular")
    fun getPopular(): Observable<MovieCollectionPojo>

    @GET("/movie/top_rated")
    fun getTopRated(): Observable<MovieCollectionPojo>

    @GET("/movie/upcoming")
    fun getUpcoming(): Observable<MovieCollectionPojo>

}