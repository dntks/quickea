package com.dtks.quickea.domain.usecase

import android.content.Context
import com.dtks.quickea.data.repository.ProductRepository
import javax.inject.Inject

class LoadProducts @Inject constructor(
    val productRepository: ProductRepository
) {
    suspend fun invoke(context: Context) {
        productRepository.loadProducts(context)
    }
}