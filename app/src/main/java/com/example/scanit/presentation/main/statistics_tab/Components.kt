package com.example.scanit.presentation.main.statistics_tab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scanit.domain.model.Product
import androidx.compose.ui.graphics.Color

@Composable
fun Statistics(
    modifier: Modifier = Modifier,
    groupedProduct: Product,
    backgroundColor: Color
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        backgroundColor = backgroundColor,
        elevation = 7.5.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Text(text = groupedProduct.name, modifier = Modifier.weight(1f))
            Text(text = groupedProduct.quantity.toString(), modifier = Modifier.weight(1f))
            Text(text = groupedProduct.price.toString(), modifier = Modifier.weight(1f))
        }
    }
}