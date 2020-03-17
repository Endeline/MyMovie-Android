package com.endeline.data.dummy

import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Singleton

/**
 * Only in memory
 */
@Singleton
class DummyRepository {

    private var entity = DummyEntity.EMPTY

    val subject = BehaviorSubject.create<DummyEntity>()

    fun save(entity: DummyEntity) {
        Timber.d("Dummy save: $entity")
        this.entity = entity
        subject.onNext(entity)
    }

    fun updateName(name: String) {
        Timber.d("Dummy update name: $name")
        entity.name = name
        subject.onNext(entity)
    }

    fun updateAge(age: Int) {
        Timber.d("Dummy update age: $age")
        entity.age = age
        subject.onNext(entity)
    }

    fun get(): DummyEntity {
        Timber.d("Dummy get: $entity")
        return entity
    }

}