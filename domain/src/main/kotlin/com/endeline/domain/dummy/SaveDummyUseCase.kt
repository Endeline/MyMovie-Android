package com.endeline.domain.dummy

import com.endeline.data.dummy.DummyRepository
import com.endeline.domain.di.components.DaggerRepositoryComponent
import io.reactivex.Completable
import timber.log.Timber
import javax.inject.Inject

class SaveDummyUseCase: CompletableUseCase<DummyUiModel> {

    @Inject
    protected lateinit var dummyRepository: DummyRepository

    init {
        DaggerRepositoryComponent.builder().build().inject(this)
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun invoke(uiModel: DummyUiModel): Completable {
        Timber.d("Dummy save uiModel: $uiModel")
        dummyRepository.save(uiModel.toEntity())

        return Completable.complete()
    }

}