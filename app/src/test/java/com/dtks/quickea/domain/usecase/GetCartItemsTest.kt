package com.dtks.quickea.domain.usecase

import com.dtks.quickea.data.repository.ProductEntityToDomainModelTransformer
import com.dtks.quickea.data.repository.ProductRepository
import com.dtks.quickea.defaultCartItem1
import com.dtks.quickea.defaultCartItem2
import com.dtks.quickea.defaultCartItemProduct1
import com.dtks.quickea.defaultCartItemProduct2
import com.dtks.quickea.domain.model.CartItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetCartItemsTest {

    private var testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)
    private val repository = mockk<ProductRepository>()
    private val modelTransformer = mockk<ProductEntityToDomainModelTransformer>()
    private val useCase = GetCartItems(repository, modelTransformer)

    @Test
    fun invokeCallsRepositoryAndModelTransformer() = testScope.runTest {
        val cartItemProducts = listOf(defaultCartItemProduct1, defaultCartItemProduct2)
        val cartItemProductFlow = flowOf(cartItemProducts)
        val cartItems = listOf(defaultCartItem1, defaultCartItem2)
        coEvery { repository.getCartItems() } returns cartItemProductFlow
        coEvery { modelTransformer.transformAllCartItems(any()) } returns cartItems

        val responseFlow = useCase.invoke()

        val collectedFlow = mutableListOf<List<CartItem>>()
        responseFlow.toCollection(collectedFlow)

        Assert.assertEquals(cartItems, collectedFlow.first())
        coVerify { repository.getCartItems() }
        coVerify { modelTransformer.transformAllCartItems(any()) }
    }
}