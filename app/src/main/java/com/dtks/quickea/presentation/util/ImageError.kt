package com.dtks.quickea.presentation.util

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dtks.quickea.R

@Composable
fun ImageError() {
    Box {

        Text(
            text = stringResource(id = R.string.image_load_error),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}