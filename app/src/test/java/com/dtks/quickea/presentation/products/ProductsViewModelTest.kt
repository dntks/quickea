package com.dtks.quickea.presentation.products

import android.content.Context
import com.dtks.quickea.defaultProduct1
import com.dtks.quickea.defaultProduct2
import com.dtks.quickea.domain.usecase.AddProductToCart
import com.dtks.quickea.domain.usecase.GetProducts
import com.dtks.quickea.domain.usecase.LoadProducts
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

class ProductsViewModelTest {

    private var testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)
    val getProducts: GetProducts = mockk()
    val loadProducts: LoadProducts = mockk()
    val addProductToCart: AddProductToCart = mockk()

    lateinit var productsViewModel: ProductsViewModel

    private val products = listOf(defaultProduct1, defaultProduct2)

    @Before
    fun setup() {
        coEvery { getProducts.invoke() } returns flowOf(products)
        coEvery { loadProducts.invoke(any()) } returns Unit
        coEvery { addProductToCart.invoke(any()) } returns Unit

        productsViewModel = ProductsViewModel(
            getProducts,
            loadProducts,
            addProductToCart,
            testDispatcher
        )
    }

    @Test
    fun initSetsProductsAndStateCorrectly() = testScope.runTest {
        val actualProducts = productsViewModel.allProducts.value
        val initialProductState = productsViewModel.productAddState.value

        Assert.assertEquals(products, actualProducts)
        Assert.assertEquals(ProductAddState.AVAILABLE, initialProductState)
    }

    @Test
    fun getProductsCallsLoadProducts() = testScope.runTest {
        val mockContext = mockk<Context>()
        productsViewModel.getProducts(mockContext)

        coVerify { loadProducts.invoke(mockContext) }
    }

    @Test
    fun addProductToCartCallsUseCase() = testScope.runTest {
        productsViewModel.addProductToCart(defaultProduct1)

        coVerify { addProductToCart.invoke(defaultProduct1) }
    }
}