package com.dtks.quickea.domain.usecase

import com.dtks.quickea.data.repository.ProductRepository
import com.dtks.quickea.domain.model.CartItem
import javax.inject.Inject

class RemoveCartItem @Inject constructor(
    val productRepository: ProductRepository
) {
    suspend fun invoke(cartItem: CartItem){
        productRepository.removeCartItem(cartItem)
    }
}