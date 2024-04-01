package com.dtks.quickea.presentation.cart

import com.dtks.quickea.defaultCartItem1
import com.dtks.quickea.defaultCartItem2
import com.dtks.quickea.defaultPrice1
import com.dtks.quickea.domain.usecase.CartItemCount
import com.dtks.quickea.domain.usecase.GetCartItems
import com.dtks.quickea.domain.usecase.RemoveCartItem
import com.dtks.quickea.domain.usecase.SumCartItemPrices
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CartViewModelTest {

    private var testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)
    val removeCartItem: RemoveCartItem = mockk()
    val sumCartItemPrices: SumCartItemPrices = mockk()
    val cartItemCount: CartItemCount = mockk()
    val getCartItems: GetCartItems = mockk()

    lateinit var cartViewModel: CartViewModel

    private val cartItems = listOf(defaultCartItem1, defaultCartItem2)
    private val prices = listOf(defaultPrice1)

    @Before
    fun setup() {
        coEvery { removeCartItem.invoke(any()) } returns Unit
        coEvery { sumCartItemPrices.invoke(any()) } returns prices
        coEvery { cartItemCount.invoke(any()) } returns "24"
        coEvery { getCartItems.invoke() } returns flowOf(cartItems)

        cartViewModel = CartViewModel(
            getCartItems,
            removeCartItem,
            sumCartItemPrices,
            cartItemCount,
            testDispatcher
        )
    }

    @Test
    fun initSetsCartItemsCorrectly() = testScope.runTest {
        val cartItemsCollection = cartViewModel.cartItems.value

        Assert.assertEquals(cartItems, cartItemsCollection)
    }

    @Test
    fun initSetsSumOfItemsCorrectly() = testScope.runTest {
        val sumOfItems = cartViewModel.sumOfItems.value

        Assert.assertEquals(prices, sumOfItems)
    }

    @Test
    fun initSetsCountOfItemsCorrectly() = testScope.runTest {
        val sumOfItems = cartViewModel.countOfItems.value

        Assert.assertEquals("24", sumOfItems)
    }

    @Test
    fun removeCallsRemoveCartItem() = testScope.runTest {
        cartViewModel.remove(defaultCartItem1)

        coVerify { removeCartItem.invoke(defaultCartItem1) }
    }
}