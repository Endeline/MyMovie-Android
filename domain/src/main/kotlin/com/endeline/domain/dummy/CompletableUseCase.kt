package com.endeline.domain.dummy

import io.reactivex.Completable

interface CompletableUseCase<P1> {
    operator fun invoke(param: P1): Completable
}