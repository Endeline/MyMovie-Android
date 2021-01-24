package com.endeline.movielibrary.domain.usecase.types

import io.reactivex.Observable

interface ObservableUseCaseWithThreeParams<P1, P2, P3> {
    operator fun invoke(param1: P1, param2: P2): Observable<P3>
}