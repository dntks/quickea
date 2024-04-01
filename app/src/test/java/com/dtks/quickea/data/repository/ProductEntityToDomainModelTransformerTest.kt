package com.dtks.quickea.data.repository

import com.dtks.quickea.defaultCartItem1
import com.dtks.quickea.defaultCartItem2
import com.dtks.quickea.defaultCartItemProduct1
import com.dtks.quickea.defaultCartItemProduct2
import com.dtks.quickea.defaultProduct1
import com.dtks.quickea.defaultProduct2
import com.dtks.quickea.defaultProductDBEntity1
import com.dtks.quickea.defaultProductDBEntity2
import org.junit.Assert
import org.junit.Test


class ProductEntityToDomainModelTransformerTest{

    private val transformer = ProductEntityToDomainModelTransformer()

    @Test
    fun transformAllCreatesCorrectProducts() {
        val expected = listOf(defaultProduct1, defaultProduct2)
        val transformed =
            transformer.transformAllProducts(listOf(defaultProductDBEntity1, defaultProductDBEntity2))

        Assert.assertEquals(expected, transformed)
    }
    @Test
    fun transformAllCartItemsCreatesCorrectCartItems() {
        val expected = listOf(defaultCartItem1, defaultCartItem2)
        val transformed =
            transformer.transformAllCartItems(listOf(defaultCartItemProduct1, defaultCartItemProduct2))

        Assert.assertEquals(expected, transformed)
    }

}