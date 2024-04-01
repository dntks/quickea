package com.dtks.quickea.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Room DB to get the products and cart.
 */
@Database(
    entities = [ProductDBEntity::class, CartItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class QuickeaDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
}