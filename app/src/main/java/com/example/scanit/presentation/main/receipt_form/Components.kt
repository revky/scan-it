package com.example.scanit.presentation.main.receipt_form

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scanit.R
import com.example.scanit.domain.model.ProductState

@Composable
fun ProductEdit(
    modifier: Modifier = Modifier,
    product: ProductState,
    onDelete: (ProductState) -> Unit,
    onNameChange: (ProductState, String) -> Unit,
    onQuantityChange: (ProductState, String) -> Unit,
    onPriceChange: (ProductState, String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        backgroundColor = Color.White,
        elevation = 7.5.dp
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                value = product.name,
                onValueChange = {
                    onNameChange(product, it)
                },
                textStyle = TextStyle(
                    fontSize = 14.sp
                ),
                maxLines = 1,
                label = { Text(text = "Name") },
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier
                        .weight(0.4f)
                        .height(50.dp),
                    value = product.quantity.toString(),
                    onValueChange = {
                        onQuantityChange(product, it)
                    },
                    textStyle = TextStyle(
                        fontSize = 14.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    maxLines = 1,
                    label = { Text(text = "Quantity") },
                )
                Spacer(modifier = Modifier.width(10.dp))
                TextField(
                    modifier = Modifier
                        .weight(0.4f)
                        .height(50.dp),
                    value = "${product.price.toDouble() / 100}",
                    onValueChange = {
                        onPriceChange(product, it)
                    },
                    textStyle = TextStyle(
                        fontSize = 14.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    maxLines = 1,
                    label = { Text(text = "Price") },
                )
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(
                    modifier = Modifier.weight(0.2f),
                    onClick = {
                        onDelete(product)
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
}

@Composable
fun ReceiptFormTopBar(
    saveReceipt: () -> Unit,
    addProduct: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "Scan It!")
        },
        actions = {
            IconButton(onClick = saveReceipt) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_save),
                    contentDescription = "menuIcon"
                )
            }
            IconButton(onClick = addProduct) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "menuIcon"
                )
            }
        }
    )
}

@Composable
fun ReceiptFormBottomBar(

) {
    //TODO 2 buttons
}