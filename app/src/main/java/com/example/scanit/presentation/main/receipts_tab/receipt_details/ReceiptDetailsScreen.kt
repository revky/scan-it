package com.example.scanit.presentation.main.receipts_tab.receipt_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.scanit.domain.model.Receipt
import com.example.scanit.util.toLongDateString

@com.ramcosta.composedestinations.annotation.Destination
@Composable
fun ReceiptDetailsScreen(
    homeScreenNavController: NavController,
    viewModel: ReceiptDetailsViewModel = hiltViewModel(),
    receiptId: String
) {
    var receipt = Receipt(
        products = listOf(
            com.example.scanit.domain.model.Product("", "Name", 0, 0)
        )
    )

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = receipt.date.toLongDateString(),
            fontSize = 24.sp
        )
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            receipt.products.forEach {
                item {
                    Product(
                        product = it,
                        backgroundColor = Color.Transparent
                    )
                }
            }
        }
    }
}
