package com.dtks.quickea.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.dtks.quickea.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    data object Product : Screen("Products", R.string.products)
    data object Cart : Screen("Cart", R.string.cart)
}
data class BottomNavigationItem(
    @StringRes val labelId: Int,
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
)
fun bottomNavigationItems() : List<BottomNavigationItem> {
    return listOf(
        BottomNavigationItem(
            labelId = Screen.Product.resourceId,
            icon = Icons.Filled.Search,
            route = Screen.Product.route
        ),
        BottomNavigationItem(
            labelId = Screen.Cart.resourceId,
            icon = Icons.Filled.ShoppingCart,
            route = Screen.Cart.route
        )
    )
}
