package com.dtks.quickea.data.repository

import com.dtks.quickea.data.api.PriceApiEntity
import com.dtks.quickea.data.api.ProductApiEntity
import com.dtks.quickea.data.local.PriceDBEntity
import com.dtks.quickea.data.local.ProductDBEntity
import com.dtks.quickea.domain.model.ProductType
import javax.inject.Inject

/**
 * Transforming Entities between the API and db layer
 */
class ProductApiToDBEntityTransformer @Inject constructor() {
    fun transform(product: ProductApiEntity): ProductDBEntity {
        val type = ProductType.fromString(product.type)
        return ProductDBEntity(
            id = product.id,
            name = product.name,
            price = transformPrice(product.price),
            type = type,
            imageUrl = product.imageUrl,
            info = product.info
        )
    }

    fun transformPrice(priceApiEntity: PriceApiEntity): PriceDBEntity {
        return PriceDBEntity(
            value = priceApiEntity.value,
            currency = priceApiEntity.currency
        )
    }

    fun transformAll(products: List<ProductApiEntity>): List<ProductDBEntity> {
        return products.mapNotNull { transform(it) }
    }
}
