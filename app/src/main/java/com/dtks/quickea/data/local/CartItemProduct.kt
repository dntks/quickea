package com.dtks.quickea.data.local

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Helper class to handle CartItem together with the referenced product.
 */
data class CartItemProduct(
    @Embedded val product: ProductDBEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId"
    )
    val cartItem: CartItemEntity
)