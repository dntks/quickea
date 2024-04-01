package com.dtks.quickea.domain.usecase

import com.dtks.quickea.data.repository.ProductEntityToDomainModelTransformer
import com.dtks.quickea.data.repository.ProductRepository
import com.dtks.quickea.defaultProduct1
import com.dtks.quickea.defaultProduct2
import com.dtks.quickea.defaultProductDBEntity1
import com.dtks.quickea.defaultProductDBEntity2
import com.dtks.quickea.domain.model.Product
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetProductsTest {

    private var testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)
    private val repository = mockk<ProductRepository>()
    private val modelTransformer = mockk<ProductEntityToDomainModelTransformer>()
    private val useCase = GetProducts(repository, modelTransformer)

    @Test
    fun invokeCallsRepositoryAndModelTransformer() = testScope.runTest {
        val productDBEntities = listOf(defaultProductDBEntity1, defaultProductDBEntity2)
        val productFlow = flowOf(productDBEntities)
        val products = listOf(defaultProduct1, defaultProduct2)
        coEvery { repository.getProducts() } returns productFlow
        coEvery { modelTransformer.transformAllProducts(any()) } returns products

        val responseFlow = useCase.invoke()

        val collectedFlow = mutableListOf<List<Product>>()
        responseFlow.toCollection(collectedFlow)

        Assert.assertEquals(products, collectedFlow.first())
        coVerify { repository.getProducts() }
        coVerify { modelTransformer.transformAllProducts(any()) }
    }

}