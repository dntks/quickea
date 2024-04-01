package com.dtks.quickea.domain.usecase

import com.dtks.quickea.defaultCartItem1
import com.dtks.quickea.defaultCartItem2
import com.dtks.quickea.defaultProduct1
import com.dtks.quickea.domain.model.Price
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import java.math.BigDecimal

class SumCartItemPricesTest {

    private var testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)
    private val useCase = SumCartItemPrices()

    @Test
    fun invokeSumsUpPricesCorrectlyWithOneCurrency() = testScope.runTest {
        val expectedPrices = listOf(
            Price(
                currency = "euro",
                value = "321.20".toBigDecimal()
            )
        )
        val prices = useCase.invoke(listOf(defaultCartItem1, defaultCartItem2))

        Assert.assertEquals(expectedPrices, prices)
    }

    @Test
    fun invokeSumsUpPricesCorrectlyWithTwoCurrencies() = testScope.runTest {
        val expectedPrices = listOf(
            Price(
                currency = "kr",
                value = "20".toBigDecimal()
            ), Price(
                currency = "euro",
                value = "80.80".toBigDecimal()
            )
        )
        val changedCurrency = defaultCartItem1.copy(
            product =
            defaultProduct1.copy(
                price = Price(
                    value = BigDecimal.TEN,
                    currency = "kr"
                )
            )
        )
        val prices = useCase.invoke(listOf(changedCurrency, defaultCartItem2))

        Assert.assertEquals(expectedPrices, prices)
    }

}