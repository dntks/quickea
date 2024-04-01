package com.dtks.quickea.data.repository

import com.dtks.quickea.defaultProductApiEntity1
import com.dtks.quickea.defaultProductApiEntity2
import com.dtks.quickea.defaultProductDBEntity1
import com.dtks.quickea.defaultProductDBEntity2
import org.junit.Assert
import org.junit.Test

class ProductApiToDBEntityTransformerTest {

    private val transformer = ProductApiToDBEntityTransformer()

    @Test
    fun transformAllCreatesCorrectDBEntities() {
        val expected = listOf(defaultProductDBEntity1, defaultProductDBEntity2)
        val transformed =
            transformer.transformAll(listOf(defaultProductApiEntity1, defaultProductApiEntity2))

        Assert.assertEquals(expected, transformed)
    }

}