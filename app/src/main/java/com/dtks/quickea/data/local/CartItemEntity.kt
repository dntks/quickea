package com.dtks.quickea.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Entity for the cart items stored in the DB.
 * Contains the referenced product's id and an amount.
 */
@Entity(
    tableName = "cartitems",
    foreignKeys = arrayOf(ForeignKey(entity = ProductDBEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("productId"),
        onDelete = ForeignKey.CASCADE))
)
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true)
    var cartItemId: Int = 0,
    var productId: String,
    var amount: Int = 1
)

