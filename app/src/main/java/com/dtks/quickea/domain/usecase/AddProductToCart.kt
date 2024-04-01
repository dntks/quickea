package com.dtks.quickea.domain.usecase

import com.dtks.quickea.data.repository.ProductRepository
import com.dtks.quickea.domain.model.Product
import javax.inject.Inject

class AddProductToCart @Inject constructor(val repository: ProductRepository){

    suspend fun invoke(product: Product){
        repository.addProductToCart(product)
    }
}