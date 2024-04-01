package com.dtks.quickea.domain.usecase

import com.dtks.quickea.domain.model.CartItem
import com.dtks.quickea.domain.model.Price
import javax.inject.Inject

/**
 * Use case for summing up prices for all cart items.
 */
class SumCartItemPrices @Inject constructor() {


    /**
     * Returns a list of prices, as they can have different currencies.
     */
    fun invoke(cartItems: List<CartItem>): List<Price> {
        val prices = cartItems.mapNotNull { cartItem ->
            cartItem.product.price.value?.let {
                Price(
                    value = it.times(cartItem.amount.toBigDecimal()),
                    currency = cartItem.product.price.currency
                )
            }
        }
        val groupedPrices = prices.groupBy { it.currency }
        return groupedPrices.keys.map {currency ->
            val values = groupedPrices[currency]?.mapNotNull { it.value } ?: emptyList()
            Price(
                value = values.sumOf { it },
                currency = currency
            )
        }
    }
}