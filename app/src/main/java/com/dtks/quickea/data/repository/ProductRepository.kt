package com.dtks.quickea.data.repository

import android.content.Context
import com.dtks.quickea.data.api.ApiDataSource
import com.dtks.quickea.data.local.CartDao
import com.dtks.quickea.data.local.CartItemProduct
import com.dtks.quickea.data.local.ProductDBEntity
import com.dtks.quickea.data.local.ProductDao
import com.dtks.quickea.domain.model.CartItem
import com.dtks.quickea.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Source of truth when it comes to data.
 */
class ProductRepository @Inject constructor(
    private val productDao: ProductDao,
    private val apiDataSource: ApiDataSource,
    private val cartDao: CartDao,
    private val entityTransformer: ProductApiToDBEntityTransformer,
) {
    fun getCartItems(): Flow<List<CartItemProduct>> {
        return cartDao.getCartItemProducts()
    }

    /**
     * Gets the products from the DB.
     * If there was a real API call, then a refresh would be needed
     * every time the products are queried.
     */
    fun getProducts(): Flow<List<ProductDBEntity>> {
        val allProducts = productDao.getAllProducts()
        return allProducts
    }

    /**
     * Needed to add separately because it needs context to load from file.
     * The DB storage of the API entities serves as an 'offline' cache, in a
     * real application when there's internet connection this would be called and the DB would be
     * refreshed with the latest data.
     */
    suspend fun loadProducts(context: Context) {
        val productApiEntities = apiDataSource.loadProducts(context)
        productDao.insertProducts(
            entityTransformer.transformAll(productApiEntities)
        )
    }

    suspend fun addProductToCart(product: Product) {
        return cartDao.addProductToCart(product)
    }

    suspend fun removeCartItem(cartItem: CartItem) {
        return cartDao.deleteCartItemById(cartItem.id)
    }
}