package com.example.scanit.presentation.main.receipts_tab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.scanit.domain.model.Product
import com.example.scanit.presentation.destinations.ReceiptDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate

@Destination
@Composable
fun ReceiptsTab(
    homeScreenNavController: NavController,
    viewModel: ReceiptsViewModel = hiltViewModel()
) {
//    Column() {
//       TODO lazy column description
//    }

    var receipt = com.example.scanit.domain.model.Receipt(
        products = listOf(
            Product(
                name = "hlep",
                quantity = 2,
                price = 600
            ),
            Product(
                name = "czew",
                quantity = 1,
                price = 100
            )
        )
    )

    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(10.dp)
    ) {
        item {
            Text(
                text = "Your receipts.",
                fontSize = 24.sp
            )
        }
        item {
            Receipt(
                onReceiptClick = {
                    homeScreenNavController.navigate( ReceiptDetailsScreenDestination(receipt.id)) {
                        launchSingleTop = true
                    }
                },
                onDeleteClick = {

                },
                receipt = receipt
            )
        }
    }
}