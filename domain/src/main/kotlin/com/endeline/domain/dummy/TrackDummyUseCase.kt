package com.endeline.domain.dummy

import com.endeline.data.dummy.DummyRepository
import com.endeline.domain.di.components.DaggerUseCaseComponent
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import javax.inject.Inject

class TrackDummyUseCase : ObservableUseCase<DummyUiModel> {

    @Inject
    protected lateinit var repository: DummyRepository

    init {
        DaggerUseCaseComponent.builder().build().inject(this)
    }

    override fun invoke(): Observable<DummyUiModel> =
        repository.subject
            .toFlowable(BackpressureStrategy.LATEST)
            .map { it.toUiModel() }
            .toObservable()
}
