package com.dtks.quickea.domain.usecase

import com.dtks.quickea.data.repository.ProductEntityToDomainModelTransformer
import com.dtks.quickea.data.repository.ProductRepository
import com.dtks.quickea.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProducts @Inject constructor(
    private  val repository: ProductRepository,
    private val modelTransformer: ProductEntityToDomainModelTransformer,
) {
    fun invoke(): Flow<List<Product>> {
        return repository.getProducts()
            .map { list -> modelTransformer.transformAllProducts(list) }
    }
}