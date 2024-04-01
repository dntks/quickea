package com.dtks.quickea.data.api

import android.content.Context
import com.dtks.quickea.R
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

/**
 * Data source for the given json. As it loads from the raw folder, it needs to access resources in the context.
 */
class ApiDataSource @Inject constructor(){
    suspend fun loadProducts(context: Context): List<ProductApiEntity> {
        val inputStream = context.resources.openRawResource(R.raw.products)
        val json = BufferedReader(InputStreamReader(inputStream)).use { it.readText() }

        val productsApiResponse: ProductsApiResponse = Gson().fromJson(json, ProductsApiResponse::class.java)
        return productsApiResponse.products
    }
}