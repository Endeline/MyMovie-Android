package com.endeline.data.service

import com.endeline.common.types.ProductType
import com.endeline.common.types.SectionType
import com.endeline.data.cache.Cache
import com.endeline.data.di.components.DaggerDataComponent
import com.endeline.data.responses.*
import com.endeline.data.repository.ProductRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductService {

    @Inject
    lateinit var cache: Cache

    @Inject
    lateinit var productRepository: ProductRepository

    init {
        DaggerDataComponent.create().inject(this)
    }

    fun getProductsWithTypes(productType: ProductType, sectionType: SectionType) =
        if (cache.contains(productType, sectionType)) {
            Observable.just(cache.get(productType, sectionType) as Products)
        } else {
            productRepository.getProductWithTypes(productType.type, sectionType.type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    cache.add(productType, sectionType, it)
                    Observable.just(it)
                }
        }

    fun getPersonDetails(id: Int): Observable<Person> =
        if (cache.contains(ProductType.PERSON, id)) {
            Observable.just(cache.get(ProductType.PERSON, id) as Person)
        } else {
            productRepository.getPersonDetails(ProductType.PERSON.type, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    cache.add(ProductType.PERSON, id, it)
                    Observable.just(it)
                }
        }

    fun getProductAdditionalInformation(
        productType: ProductType,
        id: Int,
        sectionType: SectionType
    ) = if (cache.contains(productType, id, sectionType)) {
        Observable.just(cache.get(productType, id, sectionType) as Products)
    } else {
        productRepository.getProductWithIdAndTypes(productType.type, id, sectionType.type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                cache.add(productType, id, sectionType, it)
                Observable.just(it)
            }
    }

    //todo how cache this ??
    //TypeSearch + query as key <-> result ??
    fun searchAll(query: String) = productRepository.searchAll(query)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getProductVideoLinks(productType: ProductType, id: Int) =
        if (cache.contains(productType, id, SectionType.VIDEO)) {
            Observable.just(cache.get(productType, id, SectionType.VIDEO) as VideoLinks)
        } else {
            productRepository.getProductVideoLinks(productType.type, id, SectionType.VIDEO.type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    cache.add(productType, id, SectionType.VIDEO, it)
                    Observable.just(it)
                }
        }

    fun getImages(productType: ProductType, id: Int) =
        if (cache.contains(productType, id, SectionType.IMAGES)) {
            Observable.just(cache.get(productType, id, SectionType.IMAGES) as Images)
        } else {
            productRepository.getProductImages(productType.type, id, SectionType.IMAGES.type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    cache.add(productType, id, SectionType.IMAGES, it)
                    Observable.just(it)
                }
        }

    fun getProductReviews(productType: ProductType, id: Int) =
        if (cache.contains(productType, id, SectionType.REVIEWS)) {
            Observable.just(cache.get(productType, id, SectionType.REVIEWS) as Reviews)
        } else {
            productRepository.getProductReviews(productType.type, id, SectionType.REVIEWS.type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    cache.add(productType, id, SectionType.REVIEWS, it)
                    Observable.just(it)
                }
        }

    fun getPersonCredits(productType: ProductType, id: Int) =
        if (cache.contains(productType, id, SectionType.CREDITS)) {
            Observable.just(cache.get(productType, id, SectionType.CREDITS) as PersonCredits)
        } else {
            productRepository.getPersonCredits(productType.type, id, SectionType.CREDITS.type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    cache.add(productType, id, SectionType.CREDITS, it)
                    Observable.just(it)
                }
        }

    fun getproductCredits(productType: ProductType, id: Int, sectionType: SectionType) =
        if (cache.contains(productType, id, sectionType)) {
            Observable.just(cache.get(productType, id, sectionType) as ProductCredits)
        } else {
            productRepository.getProductCredits(productType.type, id, sectionType.type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    cache.add(productType, id, sectionType, it)
                    Observable.just(it)
                }
        }

    fun getMovieDetails(productType: ProductType, id: Int) = if (cache.contains(productType, id)) {
        Observable.just(cache.get(productType, id) as ProductDetails)
    } else {
        productRepository.getProductDetails(productType.type, id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { productDetails ->
                cache.add(productType, id, productDetails)
                Observable.just(productDetails)
            }
    }
}
