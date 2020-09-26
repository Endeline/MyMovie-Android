package com.endeline.data.repository

import com.endeline.data.BuildConfig
import com.endeline.data.models.Products
import com.endeline.data.models.ProductDetails
import com.endeline.data.models.SearchAll
import com.endeline.data.models.VideoLinkCollection
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbRepository {

    @GET("${BuildConfig.API_VERSION}/{type}/now_playing")
    fun getNowPlaying(@Path("type") type: String): Observable<Products>

    @GET("${BuildConfig.API_VERSION}/{type}/popular")
    fun getPopular(@Path("type") type: String): Observable<Products>

    @GET("${BuildConfig.API_VERSION}/{type}/top_rated")
    fun getTopRated(@Path("type") type: String): Observable<Products>

    @GET("${BuildConfig.API_VERSION}/{type}/upcoming")
    fun getUpcoming(@Path("type") type: String): Observable<Products>

    @GET("${BuildConfig.API_VERSION}/{type}/on_the_air")
    fun getOnTheAir(@Path("type") type: String): Observable<Products>

    @GET("${BuildConfig.API_VERSION}/{type}/airing_today")
    fun getAiringToday(@Path("type") type: String): Observable<Products>

    @GET("${BuildConfig.API_VERSION}/movie/{id}")
    fun getMovieDetails(@Path("id") id: Int): Observable<ProductDetails>

    @GET("${BuildConfig.API_VERSION}/movie/{id}/similar")
    fun getSimilarMovies(@Path("id") id: Int): Observable<Products>

    @GET("${BuildConfig.API_VERSION}/movie/{id}/recommendations")
    fun getRecommendedMovies(@Path("id") id: Int): Observable<Products>

    @GET("${BuildConfig.API_VERSION}/movie/{id}/videos")
    fun getVideoLinks(@Path("id") id: Int): Observable<VideoLinkCollection>

    @GET("${BuildConfig.API_VERSION}/search/multi")
    fun searchAll(@Query("query") query: String): Observable<SearchAll>
}
