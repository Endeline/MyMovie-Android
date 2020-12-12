package com.endeline.domain.extensions


import com.endeline.common.ProductType
import com.endeline.data.models.Products.Product
import com.endeline.domain.uimodels.ProductsUiModel.ProductUiModel
import java.util.*

fun Product.toUiModel(productType: ProductType) = ProductUiModel(
    firstAirDate = this.firstAirDate ?: "",
    originCountry = this.originCountry ?: emptyList(),
    originalName = this.originalName ?: "",
    name = this.name ?: "",
    posterPath = this@toUiModel.posterPath ?: "",
    adult = this@toUiModel.adult ?: false,
    overview = this@toUiModel.overview ?: "",
    releaseDate = this@toUiModel.releaseDate ?: "",
    genreIds = this@toUiModel.genreIds ?: emptyList(),
    id = this@toUiModel.id ?: -1,
    originalTitle = this@toUiModel.originalTitle ?: "",
    originalLanguage = this@toUiModel.originalLanguage ?: "",
    title = this@toUiModel.title ?: "",
    backdropPath = this@toUiModel.backdropPath ?: "",
    popularity = this@toUiModel.popularity ?: -1.0,
    voteCount = this@toUiModel.voteCount ?: -1,
    video = this@toUiModel.video ?: false,
    voteAverage = this@toUiModel.voteAverage ?: -1.0,
    productType = productType
)

fun List<Product>.toUiModel(productType: ProductType) = this.map { it.toUiModel(productType) }
