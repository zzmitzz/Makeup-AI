package com.iec.makeup.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@Composable
fun rememberMakeupAppState(
    navController: NavController,
): MakeupAppState {
    return remember(navController) {
        MakeupAppState(navController)
    }
}


@Stable
class MakeupAppState @Inject constructor(
    navController: NavController,
){
    // Just store the current state of the whole application
    val isInternetConnected by mutableStateOf(checkIfConnected())
    private var _isAuthenticated = MutableStateFlow(false)
    val isAuth get() = _isAuthenticated.asStateFlow()
    private fun checkIfConnected(): Boolean {
        // TODO: Check if the device is connected to the internet
        return true
    }
}