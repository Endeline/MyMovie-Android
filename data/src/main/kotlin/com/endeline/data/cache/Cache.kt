package com.endeline.data.cache

import com.endeline.common.ProductType
import com.endeline.common.SectionType
import com.endeline.data.di.components.DaggerDataComponent
import com.endeline.data.models.*
import javax.inject.Inject

class Cache {

    @Inject
    lateinit var memoryCache: MemoryCache

    var isGetFromMemoryCache = true

    init {
        DaggerDataComponent.create().inject(this)
    }

    fun contains(productType: ProductType, sectionType: SectionType) = if (isGetFromMemoryCache) {
        memoryCache.contains(productType, sectionType)
    } else {
        //Local storege for offline
        false
    }

    fun contains(productType: ProductType, id: Int) = if (isGetFromMemoryCache) {
        memoryCache.contains(productType, id)
    } else {
        false
    }

    fun contains(productType: ProductType, id: Int, sectionType: SectionType) =
        if (isGetFromMemoryCache) {
            memoryCache.contains(productType, id, sectionType)
        } else {
            false
        }

    fun add(productType: ProductType, sectionType: SectionType, products: Products) {
        memoryCache.add(productType, sectionType, products)
        //add to local for offline
    }

    fun add(productType: ProductType, id: Int, person: Person) {
        memoryCache.add(productType, id, person)
        //add to local for offline
    }

    fun add(productType: ProductType, id: Int, sectionType: SectionType, value: Any) {
        memoryCache.add(productType, id, sectionType, value)
    }

    //todo check usGetFromMemory if else return data from room offline
    fun get(productType: ProductType, sectionType: SectionType) =
        memoryCache.get(productType, sectionType)

    fun get(productType: ProductType, id: Int) = memoryCache.get(productType, id)

    fun get(productType: ProductType, id: Int, sectionType: SectionType) =
        memoryCache.get(productType, id, sectionType)
}
