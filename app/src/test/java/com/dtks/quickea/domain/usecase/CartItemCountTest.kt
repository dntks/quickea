package com.dtks.quickea.domain.usecase

import com.dtks.quickea.data.repository.ProductRepository
import com.dtks.quickea.defaultCartItem1
import com.dtks.quickea.defaultCartItem2
import org.junit.Assert
import org.junit.Test

class CartItemCountTest {

    private val useCase = CartItemCount()

    @Test
    fun invokeCallsRepositoryAddProductToCart() {
        val countString = useCase.invoke(listOf(defaultCartItem1, defaultCartItem2))

        Assert.assertEquals("6", countString)
    }
    @Test
    fun amountOver99ReturnsCorrectString() {
        val countString = useCase.invoke(listOf(defaultCartItem1.copy(amount = 98), defaultCartItem2))

        Assert.assertEquals("99+", countString)
    }
    @Test
    fun amountZeroReturnsNull() {
        val countString = useCase.invoke(emptyList())

        Assert.assertNull(countString)
    }

}