package com.endeline.data.dummy

data class DummyEntity(
    var name: String = "",
    var age: Int = -1
) {
    companion object {
        val EMPTY = DummyEntity()
    }
}