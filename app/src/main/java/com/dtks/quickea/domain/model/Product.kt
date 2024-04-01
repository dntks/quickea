package com.dtks.quickea.domain.model

data class Product(
    val id: String,
    val name: String,
    val price: Price,
    val type: ProductType,
    val imageUrl: String,
    val info: ProductInfo?
)
