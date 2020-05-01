package com.endeline.data.services

import com.endeline.data.BuildConfig
import com.endeline.data.repository.MovieDbRepository
import com.endeline.data.models.MovieCollection
import com.endeline.data.models.MovieDetails
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

    private var cache = mutableMapOf<String, Any>().apply {
        put(CACHE_MOVIE_DETAILS, mutableListOf<MovieDetails>())
        put(CACHE_SIMILAR_MOVIES, mutableMapOf<Int, MovieCollection>())
        put(CACHE_RECOMMENDATION_MOVIES, mutableMapOf<Int, MovieCollection>())
        put(CACHE_VIDEO_LINK, mutableMapOf<Int, VideoLinkCollection>())
    }

    fun getLatest() =
        if (cache.containsKey(CACHE_LATEST)) {
            Observable.just(cache[CACHE_LATEST] as MovieDetails)
        } else {
            service.getLatest()
                .subscribeOn(Schedulers.io())
                .flatMap {
                    cache[CACHE_LATEST] = it
                    Observable.just(it)
                }
        }

    fun getNowPlaying() =
        if (cache.containsKey(CACHE_NOW_PLAYING)) {
            Observable.just(cache[CACHE_NOW_PLAYING] as MovieCollection)
        } else {
            service.getNowPlaying()
                .subscribeOn(Schedulers.io())
                .flatMap {
                    cache[CACHE_NOW_PLAYING] = it
                    Observable.just(it)
                }
        }

    fun getPopular() =
        if (cache.containsKey(CACHE_POPULAR)) {
            Observable.just(cache[CACHE_POPULAR] as MovieCollection)
        } else {
            service.getPopular()
                .subscribeOn(Schedulers.io())
                .flatMap {
                    cache[CACHE_POPULAR] = it
                    Observable.just(it)
                }
        }

    fun getTopRated() =
        if (cache.containsKey(CACHE_TOP_RATED)) {
            Observable.just(cache[CACHE_TOP_RATED] as MovieCollection)
        } else {
            service.getTopRated()
                .subscribeOn(Schedulers.io())
                .flatMap {
                    cache[CACHE_TOP_RATED] = it
                    Observable.just(it)
                }
        }

    fun getUpcoming() =
        if (cache.containsKey(CACHE_UPCOMING)) {
            Observable.just(cache[CACHE_UPCOMING] as MovieCollection)
        } else {
            service.getUpcoming()
                .subscribeOn(Schedulers.io())
                .flatMap {
                    cache[CACHE_UPCOMING] = it
                    Observable.just(it)
                }
        }

    fun getMovieDetails(id: Int): Observable<MovieDetails> {
        val details = (cache[CACHE_MOVIE_DETAILS] as List<MovieDetails>)
            .firstOrNull {
                it.id == id
            }

        return if (details != null) {
            Observable.just(details)
        } else {
            service.getMovieDetails(id)
                .subscribeOn(Schedulers.io())
                .flatMap {
                    (cache[CACHE_MOVIE_DETAILS] as MutableList<MovieDetails>).add(it)
                    Observable.just(it)
                }
        }
    }

    fun getSimilarMovies(id: Int): Observable<MovieCollection> {
        val similar = cache[CACHE_SIMILAR_MOVIES] as MutableMap<Int, MovieCollection>

        return if (similar.containsKey(id)) {
            Observable.just(similar[id])
        } else {
            service.getSimilarMovies(id)
                .subscribeOn(Schedulers.io())
                .flatMap {
                    similar[id] = it
                    Observable.just(it)
                }
        }
    }

    fun getRecommendedMovies(id: Int): Observable<MovieCollection> {
        val recommended =
            cache[CACHE_RECOMMENDATION_MOVIES] as MutableMap<Int, MovieCollection>

        return if (recommended.containsKey(id)) {
            Observable.just(recommended[id])
        } else {
            service.getRecommendedMovies(id)
                .subscribeOn(Schedulers.io())
                .flatMap {
                    recommended[id] = it
                    Observable.just(it)
                }
        }
    }

    fun getVideoLink(id: Int): Observable<VideoLinkCollection> {
        val links = cache[CACHE_VIDEO_LINK] as MutableMap<Int, VideoLinkCollection>

        return if (links.containsKey(id)) {
            Observable.just(links[id])
        } else {
            service.getVideoLinks(id)
                .subscribeOn(Schedulers.io())
                .flatMap {
                    links[id] = it
                    Observable.just(it)
                }
        }
    }

    fun searchAll(query: String) =
        service.searchAll(query)

}
