package com.endeline.movielibrary.data.service

import com.endeline.movielibrary.data.cache.Cache
import com.endeline.movielibrary.data.repository.ProductRepository
import com.endeline.movielibrary.data.responses.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductService @Inject constructor(
    private val productRepository: ProductRepository,
    private val cache: Cache
) {

    fun getProductsWithTypes(productType: com.endeline.movielibrary.common.types.ProductType, sectionType: com.endeline.movielibrary.common.types.SectionType) =
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
        if (cache.contains(com.endeline.movielibrary.common.types.ProductType.PERSON, id)) {
            Observable.just(cache.get(com.endeline.movielibrary.common.types.ProductType.PERSON, id) as Person)
        } else {
            productRepository.getPersonDetails(com.endeline.movielibrary.common.types.ProductType.PERSON.type, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    cache.add(com.endeline.movielibrary.common.types.ProductType.PERSON, id, it)
                    Observable.just(it)
                }
        }

    fun getProductAdditionalInformation(
        productType: com.endeline.movielibrary.common.types.ProductType,
        id: Int,
        sectionType: com.endeline.movielibrary.common.types.SectionType
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

    fun getProductVideoLinks(productType: com.endeline.movielibrary.common.types.ProductType, id: Int) =
        if (cache.contains(productType, id, com.endeline.movielibrary.common.types.SectionType.VIDEO)) {
            Observable.just(cache.get(productType, id, com.endeline.movielibrary.common.types.SectionType.VIDEO) as VideoLinks)
        } else {
            productRepository.getProductVideoLinks(productType.type, id, com.endeline.movielibrary.common.types.SectionType.VIDEO.type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    cache.add(productType, id, com.endeline.movielibrary.common.types.SectionType.VIDEO, it)
                    Observable.just(it)
                }
        }

    fun getImages(productType: com.endeline.movielibrary.common.types.ProductType, id: Int) =
        if (cache.contains(productType, id, com.endeline.movielibrary.common.types.SectionType.IMAGES)) {
            Observable.just(cache.get(productType, id, com.endeline.movielibrary.common.types.SectionType.IMAGES) as Images)
        } else {
            productRepository.getProductImages(productType.type, id, com.endeline.movielibrary.common.types.SectionType.IMAGES.type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    cache.add(productType, id, com.endeline.movielibrary.common.types.SectionType.IMAGES, it)
                    Observable.just(it)
                }
        }

    fun getProductReviews(productType: com.endeline.movielibrary.common.types.ProductType, id: Int) =
        if (cache.contains(productType, id, com.endeline.movielibrary.common.types.SectionType.REVIEWS)) {
            Observable.just(cache.get(productType, id, com.endeline.movielibrary.common.types.SectionType.REVIEWS) as Reviews)
        } else {
            productRepository.getProductReviews(productType.type, id, com.endeline.movielibrary.common.types.SectionType.REVIEWS.type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    cache.add(productType, id, com.endeline.movielibrary.common.types.SectionType.REVIEWS, it)
                    Observable.just(it)
                }
        }

    fun getPersonCredits(productType: com.endeline.movielibrary.common.types.ProductType, id: Int) =
        if (cache.contains(productType, id, com.endeline.movielibrary.common.types.SectionType.CREDITS)) {
            Observable.just(cache.get(productType, id, com.endeline.movielibrary.common.types.SectionType.CREDITS) as PersonCredits)
        } else {
            productRepository.getPersonCredits(productType.type, id, com.endeline.movielibrary.common.types.SectionType.CREDITS.type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    cache.add(productType, id, com.endeline.movielibrary.common.types.SectionType.CREDITS, it)
                    Observable.just(it)
                }
        }

    fun getproductCredits(productType: com.endeline.movielibrary.common.types.ProductType, id: Int, sectionType: com.endeline.movielibrary.common.types.SectionType) =
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

    fun getMovieDetails(productType: com.endeline.movielibrary.common.types.ProductType, id: Int) = if (cache.contains(productType, id)) {
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
