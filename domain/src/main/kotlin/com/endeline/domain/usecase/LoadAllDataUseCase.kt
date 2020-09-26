package com.endeline.domain.usecase

import com.endeline.data.models.Products
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
//        var nowPlaying: Products,
        var popular: Products,
        var topRated: Products,
        var upcoming: Products
    )

    @Inject
    lateinit var repository: MovieDbService

    init {
        DaggerRepositoryComponent.builder().build().inject(this)
    }

    //TODO CHANGE THIS TO LOAD ONLY HOME -> DiSCOVERY??

    override fun invoke(): Observable<Boolean> = Observable.just(true)

//        Observables.combineLatest(
////        repository.nowPlaying,
//        repository.popular,
//        repository.topRated,
//        repository.upcoming
//    ) {  popular, topRated, upcoming ->
//        ResultMapper( popular, topRated, upcoming)
//    }.subscribeOn(Schedulers.io())
//        .flatMap {
//            Observable.just(true)
//        }.doOnError {
//            Timber.e("$it")
//            Observable.just(false)
//        }
}
