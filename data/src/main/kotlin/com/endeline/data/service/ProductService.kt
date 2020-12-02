package com.endeline.data.service

import com.endeline.common.ProductType
import com.endeline.common.SectionType
import com.endeline.data.cache.Cache
import com.endeline.data.di.components.DaggerDataComponent
import com.endeline.data.models.Person
import com.endeline.data.models.ProductDetails
import com.endeline.data.models.VideoLinks
import com.endeline.data.repository.ProductRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductService {

    @Inject
    lateinit var cache: Cache

    @Inject
    lateinit var service: ProductRepository

    init {
        DaggerDataComponent.create().inject(this)
    }

    fun getProductsWithTypes(productType: ProductType, sectionType: SectionType) =
        if (cache.contains(productType, sectionType)) {
            Observable.just(cache.get(productType, sectionType))
        } else {
            service.getProductWithTypes(productType.type, sectionType.type)
                .flatMap {
                    cache.add(productType, sectionType, it)
                    Observable.just(it)
                }
        }

    fun getPersonDetails(id: Int): Observable<Person> =
        if (cache.contains(ProductType.PERSON, id)) {
            Observable.just(cache.get(ProductType.PERSON, id))
        } else {
            service.getPersonDetails(ProductType.PERSON.type, id)
                .flatMap {
                    cache.add(ProductType.PERSON, id, it)
                    Observable.just(it)
                }
        }

    fun getProductAdditionalInformation(productType: ProductType, id: Int, sectionType: SectionType) =
        if (cache.contains(productType, id, sectionType)) {
            Observable.just(cache.get(productType, id, sectionType))
        } else {
            service.getProductWithIdAndTypes(productType.type, id, sectionType.type)
                .flatMap {
                    cache.add(productType, id, sectionType, it)
                    Observable.just(it)
                }
        }

    //todo how cache this ??
    fun searchAll(query: String) = service.searchAll(query)

    fun getMovieDetails(id: Int): Observable<ProductDetails> {
        return service.getMovieDetails(id)
            .flatMap { movieDetails ->
                //todo cache
                Observable.just(movieDetails)
            }
    }

    fun getVideoLink(id: Int): Observable<VideoLinks> {
        return service.getVideoLinks(id)
            .flatMap { collection ->
                //todo cache
                Observable.just(collection)
            }
    }

    fun getVideoImages(id: Int) = service.getVideoImages(id)
        .flatMap { images ->
            //todo cache
            Observable.just(images)
        }


    fun getProductReviews(id: Int) = service.getMovieReviews(id)
        .flatMap { reviews ->
            //TODO cache
            Observable.just(reviews)
        }

    fun getProductCredits(id: Int) = service.getMovieCredits(id)
        .flatMap { credits ->
            //TODO cache
            Observable.just(credits)
        }
}
