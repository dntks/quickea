package com.dtks.quickea.presentation.products

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import com.dtks.quickea.R
import com.dtks.quickea.domain.model.ChairInfo
import com.dtks.quickea.domain.model.Price
import com.dtks.quickea.domain.model.Product
import com.dtks.quickea.domain.model.ProductType
import com.dtks.quickea.presentation.common.ProductInfoView
import com.dtks.quickea.presentation.util.ImageError
import com.dtks.quickea.presentation.util.ImageLoading
import java.math.BigDecimal

@Composable
fun ProductItem(
    product: Product,
    productAddState: ProductAddState,
    onAddToCartClick: (Product) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.card_padding))
            .wrapContentHeight()
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.card_elevation)
        )
    ) {

        Row {

            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically)
            ) {

                val imageModifier =
                    Modifier
                        .height(dimensionResource(id = R.dimen.card_image_height))
                        .aspectRatio(ratio = 1f, matchHeightConstraintsFirst = true)
                SubcomposeAsyncImage(
                    modifier = imageModifier,
                    model = product.imageUrl,
                    loading = {
                        ImageLoading(imageModifier)
                    },
                    error = {
                        ImageError()
                    },
                    contentDescription = product.name,
                    contentScale = ContentScale.FillWidth
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(dimensionResource(id = R.dimen.card_text_padding))
            ) {

                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(text = stringResource(id = product.type.displayNameRes))
                product.info?.let {
                    ProductInfoView(it)
                }
                Text(
                    text = stringResource(id = R.string.price_label, product.price.display()),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = { onAddToCartClick(product) },
                    enabled = productAddState == ProductAddState.AVAILABLE
                ) {
                    Text(text = stringResource(id = R.string.add_to_cart))
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductItemPreview() {
    ProductItem(
        product = Product(
            id = "a",
            name = "Harso",
            price = Price(value = BigDecimal.TEN, currency = "euro"),
            type = ProductType.CHAIR,
            imageUrl = "http://asd.com",
            info = ChairInfo(
                material = "wood",
                color = "brown"
            )
        ),
        ProductAddState.AVAILABLE
    ) {}
}

@Preview
@Composable
fun ProductItemUnavailableButtonPreview() {
    ProductItem(
        product = Product(
            id = "a",
            name = "Harso",
            price = Price(value = BigDecimal.TEN, currency = "euro"),
            type = ProductType.CHAIR,
            imageUrl = "http://asd.com",
            info = ChairInfo(
                material = "wood",
                color = "brown"
            )
        ),
        ProductAddState.ADDING
    ) {}
}