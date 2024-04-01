package com.dtks.quickea.domain.model

import androidx.annotation.StringRes
import com.dtks.quickea.R

enum class ProductType(@StringRes val displayNameRes: Int) {
    CHAIR(R.string.chair),
    COUCH(R.string.couch),
    UNKNOWN(R.string.unknown);

    companion object {
        fun fromString(typeString: String): ProductType {
            return when (typeString.uppercase()) {
                CHAIR.name -> CHAIR
                COUCH.name -> COUCH
                else -> UNKNOWN
            }
        }
    }
}