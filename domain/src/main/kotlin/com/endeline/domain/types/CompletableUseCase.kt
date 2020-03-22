package com.endeline.domain.types

import io.reactivex.Completable

interface CompletableUseCase {

    operator fun invoke(): Completable

}
