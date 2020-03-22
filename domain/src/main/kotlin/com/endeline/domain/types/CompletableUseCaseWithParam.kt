package com.endeline.domain.types

import io.reactivex.Completable

interface CompletableUseCaseWithParam<P1> {
    operator fun invoke(param: P1): Completable
}
