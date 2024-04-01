package com.dtks.quickea.presentation.products

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dtks.quickea.R
import com.dtks.quickea.domain.model.ChairInfo
import com.dtks.quickea.domain.model.Price
import com.dtks.quickea.domain.model.Product
import com.dtks.quickea.domain.model.ProductType
import java.math.BigDecimal


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val productsState by viewModel.allProducts.collectAsStateWithLifecycle()
    val productAddState by viewModel.productAddState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.screen_padding)),
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.products))
                }
            )
        },
        content = { paddingValues ->
            val context = LocalContext.current
            ProductsContent(
                paddingValues,
                productsState,
                productAddState,
                { viewModel.addProductToCart(it) },
                { viewModel.getProducts(context) }
            )
        }
    )
}

@Composable
fun ProductsContent(
    paddingValues: PaddingValues,
    products: List<Product>,
    productAddState: ProductAddState,
    addProductToCartClickListener: (Product) -> Unit,
    loadProducts: () -> Unit,
) {
    if (products.isEmpty()) {
        LoadingProductsView(paddingValues)
        loadProducts()
    }
    ProductsList(paddingValues, products, productAddState, addProductToCartClickListener)
}

@Composable
fun ProductsList(
    paddingValues: PaddingValues,
    products: List<Product>,
    productAddState: ProductAddState,
    addProductToCartClickListener: (Product) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        items(products) { product ->
            ProductItem(product, productAddState) {
                addProductToCartClickListener(it)
            }
        }
    }
}

@Preview
@Composable
fun ProductsContentEmptyPreview() {
    ProductsContent(
        paddingValues = PaddingValues(all = 5.dp),
        products = emptyList(),
        productAddState = ProductAddState.AVAILABLE,
        addProductToCartClickListener = {},
        loadProducts = {}
    )
}

@Preview
@Composable
fun ProductsListPreview() {
    ProductsList(
        paddingValues = PaddingValues(all = 5.dp),
        products = listOf(
            Product(
                id = "a",
                name = "Harso",
                price = Price(value = BigDecimal.TEN, currency = "euro"),
                type = ProductType.CHAIR,
                imageUrl = "http://asd.com",
                info = ChairInfo(
                    material = "wood",
                    color = "brown"
                )
            )
        ),
        productAddState = ProductAddState.AVAILABLE
    ) {

    }
}
