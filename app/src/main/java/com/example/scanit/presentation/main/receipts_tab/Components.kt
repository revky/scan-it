package com.example.scanit.presentation.main.receipts_tab

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.scanit.R
import com.example.scanit.domain.model.Receipt
import com.example.scanit.util.toDayOfTheWeek
import com.example.scanit.util.toLongDateString

@Composable
fun Receipt(
    modifier: Modifier = Modifier,
    onReceiptClick: () -> Unit,
    onDeleteClick: () -> Unit,
    receipt: Receipt
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
            .clickable {
                onReceiptClick()
            },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 7.5.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Text(text = receipt.date.toDayOfTheWeek())
            Text(text = receipt.date.toLongDateString())
            Text(text = receipt.products.sumOf {
                it.price.toDouble() / 100
            }.toString() + " zł")
            IconButton(
                onClick = {
                    onDeleteClick()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "deleteIcon",
                    tint = MaterialTheme.colors.background
                )
            }
        }
    }
}