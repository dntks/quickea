package com.dtks.quickea.domain.usecase

import com.dtks.quickea.data.repository.ProductEntityToDomainModelTransformer
import com.dtks.quickea.data.repository.ProductRepository
import com.dtks.quickea.domain.model.CartItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCartItems @Inject constructor(
    private  val repository: ProductRepository,
    private val modelTransformer: ProductEntityToDomainModelTransformer,
) {
    fun invoke(): Flow<List<CartItem>> {
        return repository.getCartItems()
            .map { list -> modelTransformer.transformAllCartItems(list) }
    }
}