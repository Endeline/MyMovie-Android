package com.endeline.domain.usecase

import com.endeline.data.models.MovieCollection
import com.endeline.data.models.MovieDetails
import com.endeline.data.repositories.MovieDbRepository
import com.endeline.domain.di.components.DaggerRepositoryComponent
import com.endeline.domain.types.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class LoadAllDataUseCase : ObservableUseCase<Boolean> {
    private data class ResultMapper(
        var nowPlaying: MovieCollection,
        var popular: MovieCollection,
        var topRated: MovieCollection,
        var upcoming: MovieCollection
    )

    @Inject
    protected lateinit var repository: MovieDbRepository

    init {
        DaggerRepositoryComponent.builder().build().inject(this)
    }

    override fun invoke(): Observable<Boolean> =
        Observables.combineLatest(
                repository.getNowPlaying(),
                repository.getPopular(),
                repository.getTopRated(),
                repository.getUpcoming()
            ) { nowPlaying, popular, topRated, upcoming ->
                ResultMapper(
                    nowPlaying,
                    popular,
                    topRated,
                    upcoming
                )
            }.subscribeOn(Schedulers.io())
            .flatMap {
                Observable.just(true)
            }.doOnError {
                Timber.d("$it")
                Observable.just(false)
            }

}
