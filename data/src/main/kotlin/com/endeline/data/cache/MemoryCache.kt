package com.endeline.data.cache

import com.endeline.common.ProductType
import com.endeline.common.SectionType
import com.endeline.data.extensions.getCacheKey
import com.endeline.data.models.*

class MemoryCache {

    private val cache = mutableMapOf<String, Any>()

    fun contains(productType: ProductType, sectionType: SectionType) =
        cache.containsKey(getCacheKey(productType.type, sectionType.type))

    fun contains(productType: ProductType, id: Int) =
        cache.containsKey(getCacheKey(productType.type, id))

    fun contains(productType: ProductType, id: Int, sectionType: SectionType) =
        cache.containsKey(getCacheKey(productType.type, id, sectionType.type))

    fun add(productType: ProductType, sectionType: SectionType, products: Products) {
        cache[getCacheKey(productType.type, sectionType.type)] = products
    }

    fun add(productType: ProductType, id: Int, person: Person) {
        cache[getCacheKey(productType.type, id)] = person
    }

    fun add(productType: ProductType, id: Int, sectionType: SectionType, value: Any) {
        cache[getCacheKey(productType.type, id, sectionType.type)] = value
    }

    fun get(productType: ProductType, sectionType: SectionType) =
        cache[getCacheKey(productType.type, sectionType.type)]

    fun get(productType: ProductType, id: Int) =
        cache[getCacheKey(productType.type, id)]

    fun get(productType: ProductType, id: Int, sectionType: SectionType) =
        cache[getCacheKey(productType.type, id, sectionType.type)]
}
