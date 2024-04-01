package com.dtks.quickea.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.dtks.quickea.domain.model.CartItem
import com.dtks.quickea.domain.model.Product
import kotlinx.coroutines.flow.Flow

/**
 * Cart data access object for Room.
 * Contains all cart related transactions.
 */
@Dao
interface CartDao {

    @Upsert
    suspend fun upsertCartItem(cartItem: CartItemEntity)

    @Transaction
    @Query("SELECT * FROM cartitems, products WHERE cartitems.productId = products.id")
    fun getCartItemProducts(): Flow<List<CartItemProduct>>

    @Query("SELECT * FROM cartitems, products WHERE cartitems.productId = :id AND products.id = :id")
    fun getCartItemByProductId(id: String): CartItemProduct?

    @Transaction
    suspend fun addProductToCart(product: Product) {
        val cartItemByProductId = getCartItemByProductId(product.id)
        val currentAmount = cartItemByProductId?.cartItem?.amount ?: 0
        val id = cartItemByProductId?.cartItem?.cartItemId ?: 0
        val cartItem = CartItemEntity(
            cartItemId = id,
            productId = product.id,
            amount = currentAmount + 1
        )
        upsertCartItem(cartItem)
    }

    @Query("DELETE FROM cartitems WHERE cartItemId = :cartId")
    suspend fun deleteCartItemById(cartId: Int)


    @Transaction
    suspend fun removeItemFromCart(cartItem: CartItem) {
        val cartItemByProductId = getCartItemByProductId(cartItem.product.id)
        cartItemByProductId?.let { cartItemProduct ->
            val currentAmount = cartItemProduct.cartItem.amount
            val updatedAmount = currentAmount - 1
            val cartItemId = cartItemProduct.cartItem.cartItemId
            if (currentAmount <= 0) {
                deleteCartItemById(cartItemId)
            } else {
                val cartItem = CartItemEntity(
                    cartItemId = cartItemId,
                    productId = cartItemByProductId.product.id,
                    amount = updatedAmount
                )
                upsertCartItem(cartItem)
            }
        }

    }
}