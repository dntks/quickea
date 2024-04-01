package com.dtks.quickea.data.repository

import android.content.Context
import com.dtks.quickea.data.api.ApiDataSource
import com.dtks.quickea.data.local.CartDao
import com.dtks.quickea.data.local.ProductDao
import com.dtks.quickea.defaultApiProducts
import com.dtks.quickea.defaultCartItem1
import com.dtks.quickea.defaultCartItemProducts
import com.dtks.quickea.defaultDBProducts
import com.dtks.quickea.defaultProduct1
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


class ProductRepositoryTest {
    private var testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)

    private val productDao = mockk<ProductDao>()
    private val apiDataSource = mockk<ApiDataSource>()
    private val cartDao = mockk<CartDao>()
    private val entityTransformer = mockk<ProductApiToDBEntityTransformer>()

    private val repository = ProductRepository(
        productDao,
        apiDataSource,
        cartDao,
        entityTransformer
    )

    @Before
    fun setup() {
        coEvery { apiDataSource.loadProducts(any()) } returns defaultApiProducts
        coEvery { entityTransformer.transformAll(any()) } returns defaultDBProducts
    }

    @Test
    fun getCartItemsReturnsTheCorrectFlow(){
        val expectedFlow = flowOf(defaultCartItemProducts)
        coEvery { cartDao.getCartItemProducts() } returns expectedFlow

        val actualFlow = repository.getCartItems()

        Assert.assertEquals(expectedFlow, actualFlow)
    }

    @Test
    fun getProductsReturnsTheCorrectFlow(){
        val expectedFlow = flowOf(defaultDBProducts)
        coEvery { productDao.getAllProducts() } returns expectedFlow

        val actualFlow = repository.getProducts()

        Assert.assertEquals(expectedFlow, actualFlow)
    }

    @Test
    fun loadProductsCallsApiDataSourceAndProductDao() = testScope.runTest{
        coEvery { apiDataSource.loadProducts(any()) } returns defaultApiProducts
        coEvery { entityTransformer.transformAll(any()) } returns defaultDBProducts
        coEvery { productDao.insertProducts(any()) } returns Unit

        val mockContext = mockk<Context>()
        repository.loadProducts(mockContext)

        coVerify { apiDataSource.loadProducts(mockContext) }
        coVerify { entityTransformer.transformAll(defaultApiProducts) }
        coVerify { productDao.insertProducts(defaultDBProducts) }
    }

    @Test
    fun addProductToCartCallsCartDao() = testScope.runTest{
        coEvery { cartDao.addProductToCart(any()) } returns Unit

        repository.addProductToCart(defaultProduct1)

        coVerify { cartDao.addProductToCart(defaultProduct1) }
    }

    @Test
    fun removeCartItemCallsCartDao() = testScope.runTest{
        coEvery { cartDao.deleteCartItemById(any()) } returns Unit

        repository.removeCartItem(defaultCartItem1)

        coVerify { cartDao.deleteCartItemById(defaultCartItem1.id) }
    }
}