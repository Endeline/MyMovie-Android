package com.endeline.domain.usecase

import com.endeline.data.models.MovieCollection
import com.endeline.data.services.MovieDbService
import com.endeline.domain.di.components.DaggerRepositoryComponent
import com.endeline.domain.usecase.types.ObservableUseCase
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
    lateinit var repository: MovieDbService

    init {
        DaggerRepositoryComponent.builder().build().inject(this)
    }

    override fun invoke(): Observable<Boolean> = Observables.combineLatest(
        repository.nowPlaying,
        repository.popular,
        repository.topRated,
        repository.upcoming
    ) { nowPlaying, popular, topRated, upcoming ->
        ResultMapper(nowPlaying, popular, topRated, upcoming)
    }.subscribeOn(Schedulers.io())
        .flatMap {
            Observable.just(true)
        }.doOnError {
            Timber.e("$it")
            Observable.just(false)
        }
}
