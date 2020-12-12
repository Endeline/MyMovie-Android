package com.endeline.domain.uimodels

data class ImagesUiModel(
    val id: Int,
    val backdrops: List<ImageUiModel>,
    val posters: List<ImageUiModel>,
    val profiles: List<ImageUiModel>
) {
    data class ImageUiModel(
        val aspectRatio: Double,
        val filePath: String,
        val height: Int,
        val iso_639_1: String,
        val voteAverage: Double,
        val voteCount: Int,
        val width: Int
    )
}
