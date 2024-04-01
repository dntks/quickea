package com.dtks.quickea.data.api

/**
 * Api entity for the price of the products, how it is in the json.
 */
data class PriceApiEntity(
    val value: String, //BigDecimal?
    val currency: String
)