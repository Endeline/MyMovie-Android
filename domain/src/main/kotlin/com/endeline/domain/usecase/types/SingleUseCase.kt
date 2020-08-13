package com.endeline.domain.usecase.types

import io.reactivex.Single

interface SingleUseCase<T> {
    operator fun invoke(): Single<T>
}