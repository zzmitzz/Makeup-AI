package com.iec.makeup.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.iec.makeup.ui.MakeupAppState


@Composable
fun NavigationGraph(
    navController: NavHostController,
    appState: MakeupAppState
) {
    NavHost(navController = navController, startDestination = "auth"){

        navigation(
            startDestination = Routes.Login.createRoute(),
            route = "auth"
        ){
            composable(route = Routes.Login.createRoute()) {
//                LoginScreen(navController = navController)
            }
            composable(route = Routes.Register.createRoute()) {
//                RegisterScreen(navController = navController)
            }
        }
    }
}