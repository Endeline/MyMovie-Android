package com.endeline.data.repository

import com.endeline.data.BuildConfig
import com.endeline.data.models.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductRepository {

    @GET("${BuildConfig.API_VERSION}/search/multi")
    fun searchAll(@Query("query") query: String): Observable<Search>

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



    //TODO in below maybe change movie in url on type and type will be provide from ui
    // and maybe change function mane from movie to product

    //only for movies?
    @GET("${BuildConfig.API_VERSION}/movie/{id}")
    fun getMovieDetails(@Path("id") id: Int): Observable<ProductDetails>

    //Probably rename all movies -> product
    @GET("${BuildConfig.API_VERSION}/movie/{id}/similar")
    fun getSimilarMovies(@Path("id") id: Int): Observable<Products>

    @GET("${BuildConfig.API_VERSION}/movie/{id}/recommendations")
    fun getRecommendedMovies(@Path("id") id: Int): Observable<Products>

    @GET("${BuildConfig.API_VERSION}/movie/{id}/videos")
    fun getVideoLinks(@Path("id") id: Int): Observable<VideoLinks>

    @GET("${BuildConfig.API_VERSION}/movie/{id}/images")
    fun getVideoImages(@Path("id") id: Int): Observable<Images>

    @GET("${BuildConfig.API_VERSION}/movie/{id}/reviews")
    fun getMovieReviews(@Path("id") id: Int): Observable<Reviews>

    @GET("${BuildConfig.API_VERSION}/movie/{id}/credits")
    fun getMovieCredits(@Path("id") id: Int): Observable<Credits>
}
