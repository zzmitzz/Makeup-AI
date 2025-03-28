package com.iec.makeup.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.iec.makeup.ui.navigation.NavigationGraph


@Composable
fun MakeupApp(
    navController: NavHostController,
    appState: MakeupAppState
) {
    return NavigationGraph(navController = navController, appState = appState)
}