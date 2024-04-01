package com.dtks.quickea.presentation.common

import androidx.compose.runtime.Composable
import com.dtks.quickea.domain.model.ChairInfo
import com.dtks.quickea.domain.model.CouchInfo
import com.dtks.quickea.domain.model.ProductInfo

@Composable
fun ProductInfoView(productInfo: ProductInfo) {
    when (productInfo) {
        is ChairInfo -> {
            ChairInfoView(productInfo)
        }

        is CouchInfo -> {
            CouchInfoView(productInfo)
        }
    }
}