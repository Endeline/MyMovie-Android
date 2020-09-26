package com.endeline.data.services

import com.endeline.data.BuildConfig
import com.endeline.data.repository.MovieDbRepository
import com.endeline.data.models.Products
import com.endeline.data.models.ProductDetails
import com.endeline.data.models.VideoLinkCollection
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
class MovieDbService {

    private val cache = mutableMapOf<String, Any>().apply {
        put(CACHE_MOVIE_DETAILS, mutableListOf<ProductDetails>())
        put(CACHE_SIMILAR_MOVIES, mutableMapOf<Int, Products>())
        put(CACHE_RECOMMENDATION_MOVIES, mutableMapOf<Int, Products>())
        put(CACHE_VIDEO_LINK, mutableMapOf<Int, VideoLinkCollection>())
    }

    fun getNowPlaying(productType: String) =
//        if (cache.containsKey(CACHE_NOW_PLAYING)) {
//        Observable.just(cache[CACHE_NOW_PLAYING] as Products)
//    } else {
        service.getNowPlaying(productType)
            .subscribeOn(Schedulers.io())
            .flatMap {
//                cache[CACHE_NOW_PLAYING] = it
                Observable.just(it)
            }
//    }

    fun getPopular(productType: String) =
//        if (cache.containsKey(CACHE_POPULAR)) {
//        Observable.just(cache[CACHE_POPULAR] as Products)
//    } else {
        service.getPopular(productType)
            .subscribeOn(Schedulers.io())
            .flatMap {
//                cache[CACHE_POPULAR] = it
                Observable.just(it)
            }
//    }

    fun getTopRated(productType: String) =
//            if (cache.containsKey(CACHE_TOP_RATED)) {
//        Observable.just(cache[CACHE_TOP_RATED] as Products)
//    } else {
        service.getTopRated(productType)
            .subscribeOn(Schedulers.io())
            .flatMap {
//                cache[CACHE_TOP_RATED] = it
                Observable.just(it)
            }
//    }

    fun getUpcoming(productType: String) =
//        if (cache.containsKey(CACHE_UPCOMING)) {
//        Observable.just(cache[CACHE_UPCOMING] as Products)
//    } else {
        service.getUpcoming(productType)
            .subscribeOn(Schedulers.io())
            .flatMap {
//                cache[CACHE_UPCOMING] = it
                Observable.just(it)
            }
//    }

    fun getOnTheAir(productType: String) =
        service.getOnTheAir(productType)
            .subscribeOn(Schedulers.io())
            .flatMap {
//                cache[CACHE_UPCOMING] = it
                Observable.just(it)
            }

    fun getAiringToday(productType: String) =
        service.getAiringToday(productType)
            .subscribeOn(Schedulers.io())
            .flatMap {
//                cache[CACHE_UPCOMING] = it
                Observable.just(it)
            }


    //todo fix
    fun getMovieDetails(id: Int): Observable<ProductDetails> {
        val details = (cache[CACHE_MOVIE_DETAILS] as List<*>).firstOrNull {
            (it as ProductDetails).id == id
        } as ProductDetails?

        return if (details != null) {
            Observable.just(details)
        } else {
            service.getMovieDetails(id)
                .subscribeOn(Schedulers.io())
                .flatMap { movieDetails ->
                    (cache[CACHE_MOVIE_DETAILS] as MutableList<ProductDetails>).add(movieDetails)
                    Observable.just(movieDetails)
                }
        }
    }

    //todo fix
    fun getSimilarMovies(id: Int): Observable<Products> {
        val similar = cache[CACHE_SIMILAR_MOVIES] as MutableMap<Int, Products>

        return if (similar.containsKey(id)) {
            Observable.just(similar[id])
        } else {
            service.getSimilarMovies(id)
                .subscribeOn(Schedulers.io())
                .flatMap { collection ->
                    similar[id] = collection
                    Observable.just(collection)
                }
        }
    }

    //todo fix
    fun getRecommendedMovies(id: Int): Observable<Products> {
        val recommended = cache[CACHE_RECOMMENDATION_MOVIES] as MutableMap<Int, Products>

        return if (recommended.containsKey(id)) {
            Observable.just(recommended[id])
        } else {
            service.getRecommendedMovies(id)
                .subscribeOn(Schedulers.io())
                .flatMap { collection ->
                    recommended[id] = collection
                    Observable.just(collection)
                }
        }
    }

    //todo fix
    fun getVideoLink(id: Int): Observable<VideoLinkCollection> {
        val links = cache[CACHE_VIDEO_LINK] as MutableMap<Int, VideoLinkCollection>

        return if (links.containsKey(id)) {
            Observable.just(links[id])
        } else {
            service.getVideoLinks(id)
                .subscribeOn(Schedulers.io())
                .flatMap { collection ->
                    links[id] = collection
                    Observable.just(collection)
                }
        }
    }

    fun searchAll(query: String) = service.searchAll(query)

    companion object {
        private const val API_KEY_PARAM = "api_key"
        private const val CACHE_MOVIE_DETAILS = "movie_details"
        private const val CACHE_LATEST = "latest"
        private const val CACHE_NOW_PLAYING = "now_playing"
        private const val CACHE_POPULAR = "popular"
        private const val CACHE_TOP_RATED = "top_rated"
        private const val CACHE_UPCOMING = "upcoming"
        private const val CACHE_SIMILAR_MOVIES = "similar_movies"
        private const val CACHE_RECOMMENDATION_MOVIES = "recommendation_movies"
        private const val CACHE_VIDEO_LINK = "video_link"

        private val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIE_DB_BASE_URL)
            .client(OkHttpClient.Builder()
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
                    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
                )
                .build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        private val service = retrofit.create(MovieDbRepository::class.java)
    }
}
