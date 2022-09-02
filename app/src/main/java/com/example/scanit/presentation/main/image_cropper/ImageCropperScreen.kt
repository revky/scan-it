package com.example.scanit.presentation.main.image_cropper

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@com.ramcosta.composedestinations.annotation.Destination
@Composable
fun ScannerScreen(
    navigator: DestinationsNavigator
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        ImageSelectorAndCropper()
    }
}