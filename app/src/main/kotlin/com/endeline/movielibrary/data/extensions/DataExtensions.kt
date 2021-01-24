package com.endeline.movielibrary.data.extensions

private const val SEPARATOR = "/"

fun getCacheKey(vararg params: String) = params.joinToString(separator = SEPARATOR)

fun getCacheKey(params: String, id: Int) = "$params$SEPARATOR$id"

fun getCacheKey(productType: String, id: Int, sectionType: String) = "$productType$SEPARATOR$id$SEPARATOR$sectionType"
