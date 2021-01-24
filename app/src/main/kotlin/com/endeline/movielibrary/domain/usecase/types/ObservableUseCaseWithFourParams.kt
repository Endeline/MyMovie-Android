package com.endeline.movielibrary.domain.usecase.types

import io.reactivex.Observable

interface ObservableUseCaseWithFourParams<P1, P2, P3, P4> {
    operator fun invoke(param1: P1, param2: P2, param3: P3): Observable<P4>
}
