package com.dtks.quickea.domain.model

data class CartItem(
    val id: Int,
    val product: Product,
    val amount: Int
)