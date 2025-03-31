package com.iec.makeup.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.iec.makeup.ui.MakeupAppState
import com.iec.makeup.ui.features.authentication.login.LoginScreen
import com.iec.makeup.ui.features.authentication.register.RegisterScreen
import com.iec.makeup.ui.features.home.HomeScreen


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
            composable(route = Routes.Login.createRoute(),
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(100)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(100)
                    )
                }) {
                appState.setVisibleBottomNav(false)
                LoginScreen(
                    navToRegister = { navController.navigate(Routes.Register.createRoute()){
                        launchSingleTop = true
                        restoreState = false
                    } }
                )
            }
            composable(route = Routes.Register.createRoute()) {

                appState.setVisibleBottomNav(false)
                RegisterScreen(
                    navBack = { navController.popBackStack() }
                )
            }
        }
        navigation(
            startDestination = Routes.Page1.createRoute(),
            route = "main"
        ){
            composable(route = Routes.Page1.createRoute()) {
                appState.setVisibleBottomNav(true)
                HomeScreen("1")
            }
            composable(route = Routes.Page2.createRoute()) {
                appState.setVisibleBottomNav(true)
                HomeScreen("1")
            }
            composable(route = Routes.Page3.createRoute()) {
                appState.setVisibleBottomNav(true)
                HomeScreen("1")
            }
            composable(route = Routes.Page4.createRoute()) {
                appState.setVisibleBottomNav(true)
                HomeScreen("1")
            }
        }
    }
}