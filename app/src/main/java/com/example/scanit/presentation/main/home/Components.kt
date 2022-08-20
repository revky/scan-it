package com.example.scanit.presentation.main.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun TopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_receipt),
            contentDescription = "applicationLogo"
        )
        Text(text = "Scan It!")
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