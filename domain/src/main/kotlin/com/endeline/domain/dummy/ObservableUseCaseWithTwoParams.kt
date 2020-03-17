package com.endeline.domain.dummy

import io.reactivex.Observable

interface ObservableUseCaseWithTwoParams<P1, P2> {
    operator fun invoke(param: P1): Observable<P2>
}