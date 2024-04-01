package com.dtks.quickea.data.api

/**
 * Represents the Api response how it is in the json
 */
data class ProductsApiResponse (
    val products: List<ProductApiEntity>
)

/**
 * Represents one product in the json.
 * The info is kept as a Map<String, String> to keep it simple.
 */
data class ProductApiEntity(
    val id: String,
    val name: String,
    val price: PriceApiEntity,
    val info: Map<String, String>,
    val type: String,
    val imageUrl: String
)

