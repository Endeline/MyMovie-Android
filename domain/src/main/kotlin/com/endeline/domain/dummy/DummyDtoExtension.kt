package com.endeline.domain.dummy

import com.endeline.data.dummy.DummyEntity

fun DummyUiModel.toEntity(): DummyEntity =
    DummyEntity().apply {
        name = this@toEntity.name
        age = this@toEntity.age
    }

fun DummyEntity.toUiModel(): DummyUiModel =
    DummyUiModel().apply {
        name = this@toUiModel.name
        age = this@toUiModel.age
    }