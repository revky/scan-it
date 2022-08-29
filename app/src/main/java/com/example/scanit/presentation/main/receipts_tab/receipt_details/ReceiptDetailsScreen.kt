package com.example.scanit.presentation.main.receipts_tab.receipt_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.scanit.presentation.common.ProgressBar
import com.example.scanit.util.Response
import com.example.scanit.util.toLongDateString

@com.ramcosta.composedestinations.annotation.Destination
@Composable
fun ReceiptDetailsScreen(
    homeScreenNavController: NavController,
    viewModel: ReceiptDetailsViewModel = hiltViewModel(),
    receiptId: String
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(10.dp)
    ) {
        when (val receiptsResponse = viewModel.receiptsState.collectAsState().value) {
            is Response.Loading -> ProgressBar()
            is Response.Success -> {
                val receipt = receiptsResponse.data?.find {
                    it.id == receiptId
                }
                Text(
                    text = receipt?.date?.toLongDateString() ?: "N/A",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                LazyColumn(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    item {
                        ProductHeader(backgroundColor = MaterialTheme.colors.primary)
                    }
                    items(receipt?.products ?: listOf()) { product ->
                        Product(product = product)
                    }
                }
            }
            is Response.Failure -> {
                //TODO TOAST ERROR
            }
        }
    }
}
