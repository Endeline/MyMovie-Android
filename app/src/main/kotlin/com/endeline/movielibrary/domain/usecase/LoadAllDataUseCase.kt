package com.endeline.movielibrary.domain.usecase

import com.endeline.movielibrary.data.responses.Products
import com.endeline.movielibrary.data.service.ProductService
import com.endeline.movielibrary.domain.usecase.types.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class LoadAllDataUseCase @Inject constructor(private val productService: ProductService) :
    ObservableUseCase<Boolean> {
    private data class ResultMapper(
//        var nowPlaying: Products,
        var popular: Products,
        var topRated: Products,
        var upcoming: Products
    )

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
