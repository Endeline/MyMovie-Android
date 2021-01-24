package com.endeline.movielibrary.data.cache

import com.endeline.movielibrary.data.responses.Person
import com.endeline.movielibrary.data.responses.ProductDetails
import com.endeline.movielibrary.data.responses.Products
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Cache @Inject constructor(private val memoryCache: MemoryCache) {

    var isGetFromMemoryCache = true

    fun contains(productType: com.endeline.movielibrary.common.types.ProductType, sectionType: com.endeline.movielibrary.common.types.SectionType) = if (isGetFromMemoryCache) {
        memoryCache.contains(productType, sectionType)
    } else {
        //Local storege for offline
        false
    }

    fun contains(productType: com.endeline.movielibrary.common.types.ProductType, id: Int) = if (isGetFromMemoryCache) {
        memoryCache.contains(productType, id)
    } else {
        false
    }

    fun contains(productType: com.endeline.movielibrary.common.types.ProductType, id: Int, sectionType: com.endeline.movielibrary.common.types.SectionType) =
        if (isGetFromMemoryCache) {
            memoryCache.contains(productType, id, sectionType)
        } else {
            false
        }

    fun add(productType: com.endeline.movielibrary.common.types.ProductType, sectionType: com.endeline.movielibrary.common.types.SectionType, products: Products) {
        memoryCache.add(productType, sectionType, products)
        //add to local for offline
    }

    fun add(productType: com.endeline.movielibrary.common.types.ProductType, id: Int, person: Person) {
        memoryCache.add(productType, id, person)
        //add to local for offline
    }

    fun add(productType: com.endeline.movielibrary.common.types.ProductType, id: Int, sectionType: com.endeline.movielibrary.common.types.SectionType, value: Any) {
        memoryCache.add(productType, id, sectionType, value)
    }

    fun add(productType: com.endeline.movielibrary.common.types.ProductType, id: Int, productDetails: ProductDetails) {
        memoryCache.add(productType, id, productDetails)
    }

    //todo check usGetFromMemory if else return data from room offline
    fun get(productType: com.endeline.movielibrary.common.types.ProductType, sectionType: com.endeline.movielibrary.common.types.SectionType) =
        memoryCache.get(productType, sectionType)

    fun get(productType: com.endeline.movielibrary.common.types.ProductType, id: Int) = memoryCache.get(productType, id)

    fun get(productType: com.endeline.movielibrary.common.types.ProductType, id: Int, sectionType: com.endeline.movielibrary.common.types.SectionType) =
        memoryCache.get(productType, id, sectionType)
}
