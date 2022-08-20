package com.example.scanit.presentation.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.example.scanit.R
import com.example.scanit.presentation.NavGraphs
import com.example.scanit.presentation.destinations.ReceiptsTabDestination
import com.ramcosta.composedestinations.DestinationsNavHost

@com.ramcosta.composedestinations.annotation.Destination
@Composable
fun HomeScreen() {
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

    Surface {
        Scaffold(
            backgroundColor = Color.Transparent,
            topBar = {
                TopBar()
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        //homeScreenNavController.navigate(CmaeraScreenDestination)
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