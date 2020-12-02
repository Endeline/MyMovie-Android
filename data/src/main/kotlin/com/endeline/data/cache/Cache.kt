package com.endeline.data.cache

import com.endeline.common.ProductType
import com.endeline.common.SectionType
import com.endeline.data.models.Person
import com.endeline.data.models.Products

class Cache {

    private val memoryCache = MemoryCache()

    var isGetFromMemoryCache = true

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

    fun add(productType: ProductType, id: Int, sectionType: SectionType, products: Products) {
        memoryCache.add(productType, id, sectionType, products)
    }

    //todo check usGetFromMemory if else return data from room offline
    fun get(productType: ProductType, sectionType: SectionType) =
        memoryCache.get(productType, sectionType) as Products

    fun get(productType: ProductType, id: Int) = memoryCache.get(productType, id) as Person

    fun get(productType: ProductType, id: Int, sectionType: SectionType) =
        memoryCache.get(productType, id, sectionType) as Products
}
