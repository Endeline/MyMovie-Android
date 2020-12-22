package com.endeline.data.repository

import com.endeline.data.BuildConfig
import com.endeline.data.responses.*
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
    fun getPersonCredits(
        @Path("type") type: String,
        @Path("id") id: Int,
        @Path("sectionType") sectionType: String
    ): Observable<PersonCredits>

    @GET("${BuildConfig.API_VERSION}/{type}/{id}/{sectionType}")
    fun getProductCredits(
        @Path("type") type: String,
        @Path("id") id: Int,
        @Path("sectionType") sectionType: String
    ): Observable<ProductCredits>

    @GET("${BuildConfig.API_VERSION}/{type}/{id}")
    fun getProductDetails(
        @Path("type") type: String,
        @Path("id") id: Int
    ): Observable<ProductDetails>

//todo as home ??
//    /person/latest
//    /person/popular
//      /tv popular/latest
//  /movie / popular/latest
}
