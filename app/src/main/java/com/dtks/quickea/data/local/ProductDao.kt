package com.dtks.quickea.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/**
 * Storing the data to have 'offline' availability for products.
 * Product data access object for Room.
 * Contains all product related transactions.
 */
@Dao
interface ProductDao {
    @Upsert
    suspend fun insertProducts(products: List<ProductDBEntity>)

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<ProductDBEntity>>
}