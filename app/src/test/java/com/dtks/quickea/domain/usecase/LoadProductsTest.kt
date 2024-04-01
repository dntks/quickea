package com.dtks.quickea.domain.usecase

import android.content.Context
import com.dtks.quickea.data.repository.ProductRepository
import com.dtks.quickea.defaultProduct1
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class LoadProductsTest {

    private var testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)
    private val repository = mockk<ProductRepository>()
    private val useCase = LoadProducts(repository)

    @Test
    fun invokeCallsRepository() = testScope.runTest {
        coEvery { repository.loadProducts(any()) } returns Unit
        val mockkContext = mockk<Context>()
        useCase.invoke(mockkContext)
        coVerify { repository.loadProducts(mockkContext) }
    }

}