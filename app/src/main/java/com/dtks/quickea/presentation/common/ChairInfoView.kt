package com.dtks.quickea.presentation.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dtks.quickea.R
import com.dtks.quickea.domain.model.ChairInfo

@Composable
fun ChairInfoView(info: ChairInfo) {
    info.color?.let {
        Text(text = stringResource(id = R.string.color_label, it))
    }
    info.material?.let {
        Text(text = stringResource(id = R.string.material_label, it))
    }
}