package com.example.scanit.presentation.main.home

import androidx.annotation.DrawableRes
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.scanit.R
import com.example.scanit.presentation.NavGraphs
import com.example.scanit.presentation.appCurrentDestinationAsState
import com.example.scanit.presentation.destinations.ReceiptsTabDestination
import com.example.scanit.presentation.destinations.StatisticsTabDestination
import com.example.scanit.presentation.startAppDestination
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    var label: String,
    @DrawableRes var iconId: Int

) {
    Receipts(
        ReceiptsTabDestination,
        "Receipts",
        R.drawable.ic_receipt
    ),
    Statistics(
        StatisticsTabDestination,
        "Statistics",
        R.drawable.ic_receipt
    ),
}

@Composable
fun TopBar(
    signOut: () -> Unit,
    revokeAccess: () -> Unit
) {
    val isOpen = remember {
        mutableStateOf(false)
    }

    TopAppBar(
        title = {
            Text(text = "Scan It!")
        },
        actions = {
            IconButton(onClick = { isOpen.value = !isOpen.value }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "menuIcon"
                )
            }
            MyDropdownMenu(
                isOpen = isOpen.value,
                signOut = {
                    signOut()
                },
                revokeAccess = {
                    revokeAccess()
                },
                changeOpenStatus = { isOpen.value = !isOpen.value })
        })
}

@Composable
fun MyDropdownMenu(
    isOpen: Boolean,
    signOut: () -> Unit,
    revokeAccess: () -> Unit,
    changeOpenStatus: () -> Unit
) {
    DropdownMenu(
        expanded = isOpen,
        onDismissRequest = { changeOpenStatus() }
    ) {
        DropdownMenuItem(onClick = {
            signOut()
        }) {
            Text(text = "Logout")
        }
        DropdownMenuItem(onClick = { revokeAccess() }) {
            Text(text = "Revoke access")
        }
    }
}

@Composable
fun BottomBar(
    navController: NavController
) {
    val currentDestination: com.example.scanit.presentation.destinations.Destination =
        navController.appCurrentDestinationAsState().value ?: NavGraphs.root.startAppDestination

    BottomNavigation {
        BottomBarDestination.values().forEach {
            BottomNavigationItem(
                selected = currentDestination == it.direction,
                onClick = {
                    navController.navigate(it.direction)
                },
                icon = {
                    Icon(painter = painterResource(id = it.iconId), contentDescription = "Bot")
                },
                label = {
                    Text(text = it.label)
                },
                alwaysShowLabel = true
            )
        }
    }
}