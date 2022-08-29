package com.example.scanit.presentation.main.receipts_tab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.scanit.presentation.destinations.ReceiptDetailsScreenDestination
import com.example.scanit.util.Response
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate

@Destination
@Composable
fun ReceiptsTab(
    homeScreenNavController: NavController,
    viewModel: ReceiptsViewModel = hiltViewModel()
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "Your receipts.",
            fontSize = 24.sp
        )

        when (val receiptsResponse = viewModel.receiptsState.collectAsState().value) {
            is Response.Loading -> ProgressBar()
            is Response.Success -> LazyColumn(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                items(receiptsResponse.data ?: listOf()) { receipt ->
                    Receipt(
                        onReceiptClick = {
                            homeScreenNavController.navigate(ReceiptDetailsScreenDestination(receipt.id)) {
                                launchSingleTop = true
                            }
                        },
                        onDeleteClick = {

                        },
                        receipt = receipt
                    )
                }
            }
            is Response.Failure -> {
                //TODO TOAST ERROR
            }
        }
    }
}