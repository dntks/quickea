package com.dtks.quickea.presentation.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dtks.quickea.R
import com.dtks.quickea.domain.model.CartItem
import com.dtks.quickea.domain.model.CouchInfo
import com.dtks.quickea.domain.model.Price
import com.dtks.quickea.domain.model.Product
import com.dtks.quickea.domain.model.ProductType
import java.math.BigDecimal

/**
 * Screen for the cart tab containing the total of all added items
 * and the list of cart items grouped by product.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    cartViewModel: CartViewModel = hiltViewModel()
) {

    val cartState by cartViewModel.cartItems.collectAsStateWithLifecycle()
    val cartSumState by cartViewModel.sumOfItems.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.screen_padding)),
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.cart))
                }
            )
        },
        content = { paddingValues ->
            if (cartState.isEmpty()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues),
                    text = stringResource(id = R.string.empty_cart)
                )
            } else {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                ) {
                    val cartSums = cartSumState.joinToString {
                        it.display()
                    }
                    CartItemsWithTotal(cartSums, cartState) {
                        cartViewModel.remove(it)
                    }
                }
            }
        }
    )
}

@Composable
private fun CartItemsWithTotal(
    cartSums: String,
    cartState: List<CartItem>,
    onRemoveClicked: (CartItem) -> Unit
) {
    Text(
        text = stringResource(id = R.string.sum_of_cart, cartSums),
        style = MaterialTheme.typography.displaySmall,
        fontWeight = FontWeight.Bold,
    )
    LazyColumn {
        items(cartState) { cartItem ->
            CartItem(cartItem) {
                onRemoveClicked(it)
            }
        }

    }
}

@Preview
@Composable
fun EmptyCartScreenPreview() {
    CartScreen()
}

@Preview
@Composable
fun CartItemsWithTotalPreview() {
    CartItemsWithTotal(
        cartSums = "Total: 1313 kr",
        cartState = listOf(
            CartItem(
                id = 2,
                product = Product(
                    id = "2",
                    name = "TestC",
                    price = Price(value = BigDecimal.TEN, currency = "kr"),
                    type = ProductType.COUCH,
                    imageUrl = "http://a.png",
                    info = CouchInfo(
                        numberOfSeats = 3,
                        color = "black"
                    )
                ),
                amount = 2
            )
        )
    ){

    }
}
