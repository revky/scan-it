package com.example.scanit.presentation.auth

import MyButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.scanit.R
import com.example.scanit.presentation.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun SignInWithGoogleScreen(
    navigator: DestinationsNavigator,
    viewModel: SignInWithGoogleViewModel = hiltViewModel()
) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "SignInImage",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .padding(20.dp)
            )
            MyButton(
                text = "Sign in with Google",
                backgroundColor = MaterialTheme.colors.background,
                textColor = MaterialTheme.colors.primary
            ) {
                navigator.navigate(HomeScreenDestination)
            }
        }
    }
}