package com.dtks.quickea.domain.model

sealed class ProductInfo{
    companion object{
        const val colorKey = "color"
        const val numberOfSeatsKey = "numberOfSeats"
        const val materialKey = "material"
    }
}

data class CouchInfo(
    val numberOfSeats : Int?,
    val color: String?
): ProductInfo()

data class ChairInfo(
    val material : String?,
    val color: String?
): ProductInfo()