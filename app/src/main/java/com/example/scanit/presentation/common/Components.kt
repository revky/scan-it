package com.example.scanit.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun ProgressBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}

@Composable
fun ConfirmCancelDialog(
    visible: Boolean,
    onValueChanged: (Boolean) -> Unit,
    title: String,
    text: String
) {
    if (visible) {
        AlertDialog(
            onDismissRequest = {
            },
            title = {
                Text(
                    text = title,
                    color = MaterialTheme.colors.primaryVariant,
                    fontSize = 24.sp
                )
            },
            confirmButton = {
                Button(onClick = {
                    onValueChanged(true)
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                Button(onClick = {
                    onValueChanged(false)
                }) {
                    Text(text = "Dismiss")
                }
            },
            text = {
                Text(
                    text = text,
                    color = MaterialTheme.colors.primary,
                    fontSize = 16.sp
                )
            }
        )
    }
}