package com.example.scanit.presentation.main.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.scanit.R
import com.example.scanit.presentation.NavGraphs
import com.example.scanit.presentation.common.ConfirmCancelDialog
import com.example.scanit.presentation.destinations.ReceiptsTabDestination
import com.example.scanit.presentation.destinations.ScannerScreenDestination
import com.example.scanit.presentation.destinations.SignInWithGoogleScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.navigate

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
//    val navBackStackEntry by homeScreenNavController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route?.substringBefore('?')
//    val topBarState =
//        !arrayOf(
//            PROFILE_TAB,
//            CAMERA_SCREEN
//        ).any { it == currentRoute }
//    viewModel.onTopBarChange(topBarState)
//    val bottomBarState =
//        !arrayOf(
//            TRIP_DETAILS_SCREEN,
//            TRIP_DAY_DETAILS_SCREEN,
//            TRIP_EVENT_DETAILS_SCREEN,
//            CAMERA_SCREEN
//        ).any { it == currentRoute }
//    viewModel.onBottomBarChange(bottomBarState)

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
                TopBar(
                    signOut = {
                        viewModel.signOut()
                        navigator.popBackStack("sign_in_with_google_screen", true)
                        navigator.navigate(SignInWithGoogleScreenDestination){
                            launchSingleTop = true
                        }
                    },
                    revokeAccess = {
                        viewModel.revokeAccess()
                        navigator.popBackStack("sign_in_with_google_screen", true)
                        navigator.navigate(SignInWithGoogleScreenDestination){
                            launchSingleTop = true
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                       homeScreenNavController.navigate(ScannerScreenDestination)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_receipt),
                        contentDescription = ""
                    )
                }
            },
            bottomBar = {
                BottomBar(navController = homeScreenNavController)
            }
        ) {
            Column(modifier = Modifier.padding(it)) {
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    navController = homeScreenNavController,
                    startRoute = ReceiptsTabDestination
                )
            }
        }
    }
}