package com.example.scanit.presentation.main.receipts_tab.receipt_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scanit.R
import com.example.scanit.domain.model.Product

@Composable
fun Product(
    modifier: Modifier = Modifier,
    product: Product
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
            .drawBehind {
                val strokeWidth = 1 * density
                val y = size.height - strokeWidth / 2

                drawLine(
                    Color.Red,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            }
    ) {
        Icon(
            modifier = Modifier.weight(0.5f),
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = "arrowIcon"
        )
        Text(
            modifier = Modifier.weight(1f),
            text = product.name,
            fontSize = 18.sp
        )
        Text(
            modifier = Modifier.weight(1f),
            text = product.quantity.toString(),
            fontSize = 18.sp
        )
        Text(
            modifier = Modifier.weight(1f),
            text = "${product.price.toDouble()/100} zł",
            fontSize = 18.sp
        )
    }
}

@Composable
fun ProductHeader(
    modifier: Modifier = Modifier,
    backgroundColor : Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .drawBehind {
                val strokeWidth = 1 * density
                val y = size.height - strokeWidth / 2

                drawLine(
                    Color.Red,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            }
            .padding(5.dp)
    ) {
        Icon(
            modifier = Modifier.weight(0.5f),
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = "arrowIcon",
            tint = Color.Transparent
        )
        Text(
            modifier = Modifier.weight(1f),
            text = "Name",
            fontSize = 18.sp
        )
        Text(
            modifier = Modifier.weight(1f),
            text = "Quantity",
            fontSize = 18.sp
        )
        Text(
            modifier = Modifier.weight(1f),
            text = "Price",
            fontSize = 18.sp
        )
    }
}