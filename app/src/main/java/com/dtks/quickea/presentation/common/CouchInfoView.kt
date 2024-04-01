package com.dtks.quickea.presentation.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dtks.quickea.R
import com.dtks.quickea.domain.model.CouchInfo

@Composable
fun CouchInfoView(info: CouchInfo) {
    info.color?.let {
        Text(text = stringResource(id = R.string.color_label, it))
    }
    info.numberOfSeats?.let {
        Text(text = stringResource(id = R.string.number_of_seats_label, it))
    }
}