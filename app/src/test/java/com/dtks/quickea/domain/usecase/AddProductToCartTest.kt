package com.dtks.quickea.domain.usecase

import com.dtks.quickea.data.repository.ProductRepository
import com.dtks.quickea.defaultProduct1
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AddProductToCartTest {

    private var testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)
    private val repository = mockk<ProductRepository>()
    private val useCase = AddProductToCart(repository)

    @Test
    fun invokeCallsRepositoryAddProductToCart() = testScope.runTest {
        coEvery { repository.addProductToCart(any()) } returns Unit
        useCase.invoke(defaultProduct1)
        coVerify { repository.addProductToCart(defaultProduct1) }
    }

}