package com.dtks.quickea.data.repository

import com.dtks.quickea.data.local.CartItemProduct
import com.dtks.quickea.data.local.PriceDBEntity
import com.dtks.quickea.data.local.ProductDBEntity
import com.dtks.quickea.domain.model.CartItem
import com.dtks.quickea.domain.model.ChairInfo
import com.dtks.quickea.domain.model.CouchInfo
import com.dtks.quickea.domain.model.Price
import com.dtks.quickea.domain.model.Product
import com.dtks.quickea.domain.model.ProductInfo
import com.dtks.quickea.domain.model.ProductType
import javax.inject.Inject

/**
 * Transforming Entities between the db and domain layer
 */
class ProductEntityToDomainModelTransformer @Inject constructor() {
    fun transformProduct(product: ProductDBEntity): Product {
        val type = product.type
        return Product(
            id = product.id,
            name = product.name,
            price = transformPrice(product.price),
            type = type,
            imageUrl = product.imageUrl,
            info = product.info?.let { transformInfo(type, it) }
        )
    }

    fun transformPrice(priceDBEntity: PriceDBEntity): Price {
        val valueDecimal = priceDBEntity.value.toBigDecimalOrNull()
        return Price(
            value = valueDecimal,
            currency = valueDecimal?.let { priceDBEntity.currency } ?: ""
        )
    }

    private fun transformInfo(productType: ProductType, info: Map<String, String>): ProductInfo? {
        return when (productType){
            ProductType.CHAIR -> ChairInfo(
                material = info[ProductInfo.materialKey],
                color = info[ProductInfo.colorKey]
            )
            ProductType.COUCH -> CouchInfo(
                numberOfSeats = info[ProductInfo.numberOfSeatsKey]?.toIntOrNull(),
                color = info[ProductInfo.colorKey]
            )

            else -> null
        }
    }

    fun transformAllProducts(products: List<ProductDBEntity>): List<Product> {
        return products.mapNotNull { transformProduct(it) }
    }

    fun transformAllCartItems(products: List<CartItemProduct>): List<CartItem> {
        return products.mapNotNull {
            CartItem(
                it.cartItem.cartItemId,
                transformProduct(it.product),
                it.cartItem.amount
            )
        }
    }
}