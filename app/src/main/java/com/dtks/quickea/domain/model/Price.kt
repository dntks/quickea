package com.dtks.quickea.domain.model

import java.math.BigDecimal

data class Price(
    val value: BigDecimal?,
    val currency: String
) {
    fun display(): String {
        return "$value $currency"
    }
}