package com.endeline.domain.dummy

import io.reactivex.Observable

interface ObservableUseCase<P1> {
    operator fun invoke(): Observable<P1>
}