package com.endeline.movielibrary.domain.usecase.types

import io.reactivex.Observable

interface ObservableUseCaseWithTwoParams<P1, P2> {
    operator fun invoke(param: P1): Observable<P2>
}