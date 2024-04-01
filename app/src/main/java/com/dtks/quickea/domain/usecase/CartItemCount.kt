package com.dtks.quickea.domain.usecase

import com.dtks.quickea.domain.model.CartItem
import javax.inject.Inject

/**
 * Use case for counting the items in the cart to be shown on the tab badge.
 */
class CartItemCount @Inject constructor() {

    fun invoke(cartItems: List<CartItem>): String? {
        val sumOf = cartItems.sumOf { it.amount }
        return if (sumOf > 99) "99+"
        else if (sumOf <= 0) null
        else sumOf.toString()
    }
}