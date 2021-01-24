package com.endeline.movielibrary.data.cache

import com.endeline.movielibrary.data.extensions.getCacheKey
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoryCache @Inject constructor() {

    private val cache = mutableMapOf<String, Any>()

    fun contains(productType: com.endeline.movielibrary.common.types.ProductType, sectionType: com.endeline.movielibrary.common.types.SectionType) =
        cache.containsKey(getCacheKey(productType.type, sectionType.type))

    fun contains(productType: com.endeline.movielibrary.common.types.ProductType, id: Int) =
        cache.containsKey(getCacheKey(productType.type, id))

    fun contains(productType: com.endeline.movielibrary.common.types.ProductType, id: Int, sectionType: com.endeline.movielibrary.common.types.SectionType) =
        cache.containsKey(getCacheKey(productType.type, id, sectionType.type))

    fun add(productType: com.endeline.movielibrary.common.types.ProductType, sectionType: com.endeline.movielibrary.common.types.SectionType, value: Any) {
        cache[getCacheKey(productType.type, sectionType.type)] = value
    }

    fun add(productType: com.endeline.movielibrary.common.types.ProductType, id: Int, value: Any) {
        cache[getCacheKey(productType.type, id)] = value
    }

    fun add(productType: com.endeline.movielibrary.common.types.ProductType, id: Int, sectionType: com.endeline.movielibrary.common.types.SectionType, value: Any) {
        cache[getCacheKey(productType.type, id, sectionType.type)] = value
    }

    fun get(productType: com.endeline.movielibrary.common.types.ProductType, sectionType: com.endeline.movielibrary.common.types.SectionType) =
        cache[getCacheKey(productType.type, sectionType.type)]

    fun get(productType: com.endeline.movielibrary.common.types.ProductType, id: Int) =
        cache[getCacheKey(productType.type, id)]

    fun get(productType: com.endeline.movielibrary.common.types.ProductType, id: Int, sectionType: com.endeline.movielibrary.common.types.SectionType) =
        cache[getCacheKey(productType.type, id, sectionType.type)]
}
