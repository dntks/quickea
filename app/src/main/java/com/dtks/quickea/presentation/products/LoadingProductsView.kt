package com.dtks.quickea.presentation.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dtks.quickea.R

@Composable
fun LoadingProductsView(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.progress_indicator_size)),
            color = MaterialTheme.colorScheme.secondary,
        )
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(paddingValues),
            text = stringResource(id = R.string.loading_products)
        )
    }
}

@Preview
@Composable
fun LoadingProductsPreview(){
    LoadingProductsView(paddingValues = PaddingValues(3.dp))
}