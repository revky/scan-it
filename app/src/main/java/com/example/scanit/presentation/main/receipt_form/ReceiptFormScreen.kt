package com.example.scanit.presentation.main.receipt_form

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.scanit.presentation.common.ProgressBar
import com.example.scanit.util.Response
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ReceiptFormScreen(
    navigator: DestinationsNavigator,
    viewModel: ReceiptFormViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            backgroundColor = Color.Transparent,
            topBar = {
                ReceiptFormTopBar(
                    saveReceipt = {},
                    addProduct = {}
                )
            },
            bottomBar = {
                ReceiptFormBottomBar()
            }
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(it)
                    .padding(10.dp)
                    .fillMaxHeight()
            ) {
                Log.e("tag",viewModel.imageUploadState.collectAsState().value.toString())
                when (val receiptsResponse = viewModel.imageUploadState.collectAsState().value) {
                    is Response.Loading -> ProgressBar()
                    is Response.Success -> LazyColumn(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        item {
                            Text(
                                text = "Products",
                                fontSize = 24.sp,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                        }
                        items(receiptsResponse.data ?: listOf()) { product ->
                            ProductEdit(onDeleteClick = {}, product = product)
                        }
                    }
                    is Response.Failure -> {
                        //TODO TOAST ERROR
                    }
                }
            }
        }
    }
}