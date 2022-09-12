package com.example.scanit.presentation.main.home

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.net.toFile
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.example.scanit.R
import com.example.scanit.presentation.NavGraphs
import com.example.scanit.presentation.common.ConfirmCancelDialog
import com.example.scanit.presentation.destinations.ReceiptFormScreenDestination
import com.example.scanit.presentation.destinations.ReceiptsTabDestination
import com.example.scanit.presentation.destinations.SignInWithGoogleScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.io.File

@com.ramcosta.composedestinations.annotation.Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val logoutDialogState = remember {
        mutableStateOf(false)
    }

    val homeScreenNavController = rememberNavController()
    val context = LocalContext.current
    val imageCropLauncher =
        rememberLauncherForActivityResult(contract = CropImageContract()) { result ->
            if (result.isSuccessful) {
                // use the cropped image
                val resultImageUri = result.uriContent
                val imageFilePath =
                    result.getUriFilePath(context = context, uniqueName = true).toString()
                if (resultImageUri?.scheme?.contains("content") == true) {
                    // replace Scheme to file
                    val builder = Uri.Builder()
                    builder.scheme("file")
                        .appendPath(imageFilePath)
                    val imageUri = builder.build()
                    val file: File = imageUri.toFile()
                    viewModel.uploadImage(file)
                    navigator.navigate(ReceiptFormScreenDestination)
                }
            } else {
                result.error
            }
        }

    BackHandler(enabled = true) {
        logoutDialogState.value = !logoutDialogState.value
    }

    Surface {
        ConfirmCancelDialog(
            visible = logoutDialogState.value,
            onValueChanged = {
                logoutDialogState.value = !logoutDialogState.value
                if (it) {
                    viewModel.signOut()
                    navigator.popBackStack("sign_in_with_google_screen", true)
                    navigator.navigate(SignInWithGoogleScreenDestination) {
                        launchSingleTop = true
                    }
                }
            },
            title = "Log out",
            text = "Do you want to log out?"
        )
        Scaffold(
            backgroundColor = Color.Transparent,
            topBar = {
                HomeTopBar(
                    signOut = {
                        viewModel.signOut()
                        navigator.popBackStack("sign_in_with_google_screen", true)
                        navigator.navigate(SignInWithGoogleScreenDestination) {
                            launchSingleTop = true
                        }
                    },
                    revokeAccess = {
                        viewModel.revokeAccess()
                        navigator.popBackStack("sign_in_with_google_screen", true)
                        navigator.navigate(SignInWithGoogleScreenDestination) {
                            launchSingleTop = true
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        imageCropLauncher.launch(options {
                            setImageSource(
                                includeGallery = true,
                                includeCamera = true,
                            ); setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                            setActivityTitle("Send image")
                        })
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_receipt),
                        contentDescription = ""
                    )
                }
            },
            bottomBar = {
                HomeBottomBar(navController = homeScreenNavController)
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    navController = homeScreenNavController,
                    startRoute = ReceiptsTabDestination
                )
            }
        }
    }
}