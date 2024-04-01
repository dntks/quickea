package com.dtks.quickea

import com.dtks.quickea.data.api.PriceApiEntity
import com.dtks.quickea.data.api.ProductApiEntity
import com.dtks.quickea.data.local.CartItemEntity
import com.dtks.quickea.data.local.CartItemProduct
import com.dtks.quickea.data.local.PriceDBEntity
import com.dtks.quickea.data.local.ProductDBEntity
import com.dtks.quickea.domain.model.CartItem
import com.dtks.quickea.domain.model.ChairInfo
import com.dtks.quickea.domain.model.CouchInfo
import com.dtks.quickea.domain.model.Price
import com.dtks.quickea.domain.model.Product
import com.dtks.quickea.domain.model.ProductType
import com.dtks.quickea.presentation.navigation.Screen
import java.math.BigDecimal


val defaultProductDBEntity1 = ProductDBEntity(
    id = "1",
    name = "product1",
    price = PriceDBEntity(
        value = "120.20",
        currency = "euro"
    ),
    type = ProductType.CHAIR,
    imageUrl = "http://1.png",
    info = mapOf(
        Pair("color", "black"),
        Pair("material", "wood")
    )
)
val defaultProductApiEntity1 = ProductApiEntity(
    id = "1",
    name = "product1",
    price = PriceApiEntity(
        value = "120.20",
        currency = "euro"
    ),
    type = "chair",
    imageUrl = "http://1.png",
    info = mapOf(
        Pair("color", "black"),
        Pair("material", "wood")
    )
)
val defaultProductDBEntity2 = ProductDBEntity(
    id = "2",
    name = "product2",
    price = PriceDBEntity(
        value = "20.20",
        currency = "euro"
    ),
    type = ProductType.COUCH,
    imageUrl = "http://2.png",
    info = mapOf(
        Pair("color", "black"),
        Pair("numberOfSeats", "3")
    )
)
val defaultProductApiEntity2 = ProductApiEntity(
    id = "2",
    name = "product2",
    price = PriceApiEntity(
        value = "20.20",
        currency = "euro"
    ),
    type = "couch",
    imageUrl = "http://2.png",
    info = mapOf(
        Pair("color", "black"),
        Pair("numberOfSeats", "3")
    )
)
val defaultPrice1 = Price(
    value = "120.20".toBigDecimal(),
    currency = "euro"
)
val defaultProduct1 = Product(
    id = "1",
    name = "product1",
    price = defaultPrice1,
    type = ProductType.CHAIR,
    imageUrl = "http://1.png",
    info = ChairInfo(
        material = "wood",
        color = "black"
    )
)
val defaultPrice2 = Price(
    value = "20.20".toBigDecimal(),
    currency = "euro"
)
val defaultProduct2 = Product(
    id = "2",
    name = "product2",
    price = defaultPrice2,
    type = ProductType.COUCH,
    imageUrl = "http://2.png",
    info = CouchInfo(
        numberOfSeats = 3,
        color = "black"
    )
)
val defaultDBProducts = listOf(
    defaultProductDBEntity1,
    defaultProductDBEntity2
)

val defaultApiProducts = listOf(
    defaultProductApiEntity1,
    defaultProductApiEntity2
)
val cartItemEntity1 = CartItemEntity(
    cartItemId = 1,
    productId = defaultProductDBEntity1.id,
    amount = 2
)
val defaultCartItem1 = CartItem(
    id = 1,
    product = defaultProduct1,
    amount = 2
)
val cartItemEntity2 = CartItemEntity(
    cartItemId = 2,
    productId = defaultProductDBEntity2.id,
    amount = 4
)
val defaultCartItem2 = CartItem(
    id = 2,
    product = defaultProduct2,
    amount = 4
)
val defaultCartItemProduct1 = CartItemProduct(
    product = defaultProductDBEntity1,
    cartItem = cartItemEntity1
)
val defaultCartItemProduct2 = CartItemProduct(
    product = defaultProductDBEntity2,
    cartItem = cartItemEntity2
)
val defaultCartItemProducts = listOf(
    defaultCartItemProduct1,
    defaultCartItemProduct2
)
