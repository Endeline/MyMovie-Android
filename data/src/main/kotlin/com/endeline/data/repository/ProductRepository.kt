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

    @GET("${BuildConfig.API_VERSION}/{type}/{personId}")
    fun getPersonDetails(
        @Path("type") type: String,
        @Path("personId") personId: Int
    ): Observable<Person>

    @GET("${BuildConfig.API_VERSION}/{type}/{sectionType}")
    fun getProductWithTypes(
        @Path("type") type: String,
        @Path("sectionType") sectionType: String
    ): Observable<Products>

    @GET("${BuildConfig.API_VERSION}/{type}/{id}/{sectionType}")
    fun getProductWithIdAndTypes(
        @Path("type") type: String,
        @Path("id") id: Int,
        @Path("sectionType") sectionType: String
    ): Observable<Products>

    @GET("${BuildConfig.API_VERSION}/{type}/{id}/{sectionType}")
    fun getProductVideoLinks(
        @Path("type") type: String,
        @Path("id") id: Int,
        @Path("sectionType") sectionType: String
    ): Observable<VideoLinks>

    @GET("${BuildConfig.API_VERSION}/{type}/{id}/{sectionType}")
    fun getProductImages(
        @Path("type") type: String,
        @Path("id") id: Int,
        @Path("sectionType") sectionType: String
    ): Observable<Images>

    @GET("${BuildConfig.API_VERSION}/{type}/{id}/{sectionType}")
    fun getProductReviews(
        @Path("type") type: String,
        @Path("id") id: Int,
        @Path("sectionType") sectionType: String
    ): Observable<Reviews>

    @GET("${BuildConfig.API_VERSION}/{type}/{id}/{sectionType}")
    fun getProductCredits(
        @Path("type") type: String,
        @Path("id") id: Int,
        @Path("sectionType") sectionType: String
    ): Observable<Credits>


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
}
