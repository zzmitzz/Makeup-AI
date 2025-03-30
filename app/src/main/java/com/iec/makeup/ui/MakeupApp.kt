package com.iec.makeup.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.iec.makeup.ui.navigation.NavigationGraph


@Composable
fun MakeupApp(
    navController: NavHostController,
    appState: MakeupAppState
) {
    return Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NavigationGraph(navController = navController, appState = appState)
        if (!appState.isInternetConnected) {

        }
    }
}