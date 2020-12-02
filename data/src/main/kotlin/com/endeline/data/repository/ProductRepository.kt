package com.endeline.data.repository

import com.endeline.data.BuildConfig
import com.endeline.data.models.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductRepository {

    //probably do not change this
    @GET("${BuildConfig.API_VERSION}/search/multi")
    fun searchAll(@Query("query") query: String): Observable<Search>

    @GET("${BuildConfig.API_VERSION}/person/{personId}")
    fun getPersonDetails(@Path("personId") personId: Int): Observable<Person>

    @GET("${BuildConfig.API_VERSION}/{type}/{sectionType}")
    fun getProductWithTypes(@Path("type") type: String, @Path("sectionType") sectionType: String): Observable<Products>


//todo
//    /person/latest
//    /person/popular
//    below as new item cast?
//    /person/{person_id}/movie_credits
//    /person/{person_id}/tv_credits



    //TODO in below maybe change movie in url on type and type will be provide from ui
    // and maybe change function mane from movie to product

    //only for movies?
    @GET("${BuildConfig.API_VERSION}/movie/{id}")
    fun getMovieDetails(@Path("id") id: Int): Observable<ProductDetails>


    //probably refactor to movie/tv as parametr like apiversion/{type}/{id}/{what}
    //Probably rename all movies -> product
    @GET("${BuildConfig.API_VERSION}/movie/{id}/similar")
    fun getSimilarMovies(@Path("id") id: Int): Observable<Products>

    @GET("${BuildConfig.API_VERSION}/movie/{id}/recommendations")
    fun getRecommendedMovies(@Path("id") id: Int): Observable<Products>



    //probably change movie as parametr movie/tv
    @GET("${BuildConfig.API_VERSION}/movie/{id}/videos")
    fun getVideoLinks(@Path("id") id: Int): Observable<VideoLinks>

    @GET("${BuildConfig.API_VERSION}/movie/{id}/images")
    fun getVideoImages(@Path("id") id: Int): Observable<Images>

    @GET("${BuildConfig.API_VERSION}/movie/{id}/reviews")
    fun getMovieReviews(@Path("id") id: Int): Observable<Reviews>

    @GET("${BuildConfig.API_VERSION}/movie/{id}/credits")
    fun getMovieCredits(@Path("id") id: Int): Observable<Credits>
}
