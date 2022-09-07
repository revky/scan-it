package com.example.scanit.presentation.main.statistics_tab

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
import com.example.scanit.domain.model.Product
import com.example.scanit.presentation.common.ProgressBar
import com.example.scanit.util.Response
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun StatisticsTab(
    homeScreenNavController: NavController,
    viewModel: StatisticsViewModel = hiltViewModel()
) {
    val receiptsResponse = viewModel.receiptsState.collectAsState().value
    val allProducts = if (receiptsResponse is Response.Success) receiptsResponse.data?.flatMap {
        it.products.toList()
    } else null

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "Your statistics.",
            fontSize = 24.sp
        )
        when (receiptsResponse) {
            is Response.Loading -> ProgressBar()
            is Response.Success -> LazyColumn(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                items(allProducts?.groupBy {
                    it.name
                }?.values!!.map {
                    Product("", it[0].name, it.sumOf { product->product.quantity }, it.sumOf { product -> product.price })
                    // TODO fix this
                }) {
                    Statistics(groupedProduct = it, backgroundColor = viewModel.statColor(it.price.toDouble(), allProducts.sumOf { product -> product.price }.toDouble()))
                }
            }
            is Response.Failure -> {
                //TODO TOAST ERROR
            }
        }
    }
}