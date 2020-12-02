package com.endeline.data.service

import com.endeline.common.ProductType
import com.endeline.common.SectionType
import com.endeline.data.BuildConfig
import com.endeline.data.cache.Cache
import com.endeline.data.models.*
import com.endeline.data.repository.ProductRepository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
class ProductService {

    //todo di
    private val cache = Cache()

    fun getProductsWithTypes(productType: ProductType, sectionType: SectionType) =
        if (cache.contains(productType, sectionType)) {
            Observable.just(cache.get(productType, sectionType))
        } else {
            service.getProductWithTypes(productType.type, sectionType.type)
                .flatMap {
                    cache.add(productType, sectionType, it)
                    Observable.just(it)
                }
        }

    fun getPersonDetails(id: Int): Observable<Person> =
        if (cache.contains(ProductType.PERSON, id)) {
            Observable.just(cache.get(ProductType.PERSON, id))
        } else {
            service.getPersonDetails(ProductType.PERSON.type, id)
                .flatMap {
                    cache.add(ProductType.PERSON, id, it)
                    Observable.just(it)
                }
        }

    fun getProductAdditionalInformation(productType: ProductType, id: Int, sectionType: SectionType) =
        if (cache.contains(productType, id, sectionType)) {
            Observable.just(cache.get(productType, id, sectionType))
        } else {
            service.getProductWithIdAndTypes(productType.type, id, sectionType.type)
                .flatMap {
                    cache.add(productType, id, sectionType, it)
                    Observable.just(it)
                }
        }

    //todo how cache this ??
    fun searchAll(query: String) = service.searchAll(query)

    fun getMovieDetails(id: Int): Observable<ProductDetails> {
        return service.getMovieDetails(id)
            .flatMap { movieDetails ->
                //todo cache
                Observable.just(movieDetails)
            }
    }

    fun getVideoLink(id: Int): Observable<VideoLinks> {
        return service.getVideoLinks(id)
            .flatMap { collection ->
                //todo cache
                Observable.just(collection)
            }
    }

    fun getVideoImages(id: Int) = service.getVideoImages(id)
        .flatMap { images ->
            //todo cache
            Observable.just(images)
        }


    fun getProductReviews(id: Int) = service.getMovieReviews(id)
        .flatMap { reviews ->
            //TODO cache
            Observable.just(reviews)
        }

    fun getProductCredits(id: Int) = service.getMovieCredits(id)
        .flatMap { credits ->
            //TODO cache
            Observable.just(credits)
        }


    companion object {
        private const val API_KEY_PARAM = "api_key"
        private const val TIMEOUT_SECOND = 30L

        //TODO di
        private val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIE_DB_BASE_URL)
            .client(OkHttpClient.Builder()
                .readTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS)
                .addInterceptor {
                    var request = it.request()

                    val url = request.url()
                        .newBuilder()
                        .addQueryParameter(API_KEY_PARAM, BuildConfig.MOVIE_API_KEY)
                        .build()

                    request = request.newBuilder()
                        .url(url)
                        .build()

                    it.proceed(request)
                }
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) {
                            HttpLoggingInterceptor.Level.BODY
                        } else {
                            HttpLoggingInterceptor.Level.NONE
                        }
                    }
                )
                .build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        private val service = retrofit.create(ProductRepository::class.java)
    }
}
