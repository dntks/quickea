package com.dtks.quickea.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.dtks.quickea.domain.model.ProductType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Entity for the products in the DB.
 * The info part is kept as a Map, to keep it simple.
 * Other options considered:
 * - Having a different table for each info Type -
 */
@Entity(
    tableName = "products"
)
@TypeConverters(MapTypeConverter::class)
data class ProductDBEntity (
    @PrimaryKey
    var id: String,
    var name: String,
    @Embedded
    var price: PriceDBEntity,
    var type: ProductType,
    var imageUrl: String,
    @TypeConverters(MapTypeConverter::class)
    val info: Map<String, String>? = null
)

/**
 * Price for storing in DB.
 * value is stored as String, using it as a number only in domain.
 */
data class PriceDBEntity(
    val value: String, //BigDecimal?
    val currency: String
)

class MapTypeConverter {

    @TypeConverter
    fun stringToMap(value: String): Map<String, String> {
        return Gson().fromJson(value,  object : TypeToken<Map<String, String>>() {}.type)
    }

    @TypeConverter
    fun mapToString(value: Map<String, String>?): String {
        return if(value == null) "" else Gson().toJson(value)
    }
}

