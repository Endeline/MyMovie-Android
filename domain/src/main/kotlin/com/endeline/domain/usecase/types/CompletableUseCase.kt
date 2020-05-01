package com.endeline.domain.usecase.types

import io.reactivex.Completable

interface CompletableUseCase {

    operator fun invoke(): Completable

}
