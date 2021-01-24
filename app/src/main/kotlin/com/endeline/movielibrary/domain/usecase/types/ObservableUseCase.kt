package com.endeline.movielibrary.domain.usecase.types

import io.reactivex.Observable

interface ObservableUseCase<P1> {
    operator fun invoke(): Observable<P1>
}