package com.iec.makeup.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.material3.Text
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
import com.iec.makeup.ui.features.home.notification.NotificationContent
import com.iec.makeup.ui.features.home.search.SearchScreen
import com.iec.makeup.ui.features.home.see_all_makeup.AllMakeUpScreen


@Composable
fun NavigationGraph(
    navController: NavHostController,
    appState: MakeupAppState
) {
    NavHost(navController = navController, startDestination = "auth") {

        navigation(
            startDestination = Routes.Login.createRoute(),
            route = "auth"
        ) {
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
                    navToRegister = {
                        navController.navigate(Routes.Register.createRoute()) {
                            launchSingleTop = true
                            restoreState = false
                        }
                    },
                    navToHome = {
                        navController.navigate("main") {
                            popUpTo("auth") {
                                inclusive = false
                            }
                            launchSingleTop = true
                            restoreState = false
                        }
                    }
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
        ) {


            composable(route = Routes.Page1.createRoute()) {
                appState.setVisibleBottomNav(true)
                HomeScreen(
                    navToNotification = {
                        navController.navigate(Routes.Notification.createRoute()) {
                            launchSingleTop = true
                            restoreState = false
                        }
                    },
                    navToSearch = {
                        navController.navigate(Routes.Search.createRoute()) {
                            launchSingleTop = true
                            restoreState = false
                        }
                    },
                    navToAllMakeUp = {
                        navController.navigate(Routes.AllMakeUp.createRoute()) {
                            launchSingleTop = true
                            restoreState = false
                        }
                    }
                )
            }
            composable(route = Routes.Page2.createRoute()) {
                appState.setVisibleBottomNav(true)
                Text("Page 2")
            }
            composable(route = Routes.Page3.createRoute()) {
                appState.setVisibleBottomNav(true)
                Text("Page 3")
            }
            composable(route = Routes.Page4.createRoute()) {
                appState.setVisibleBottomNav(true)
                Text("Page 4")
            }
            composable(route = Routes.Notification.createRoute(),
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(100)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(100)
                    )
                }) {
                appState.setVisibleBottomNav(true)
                NotificationContent(
                    navBack = { navController.popBackStack() }
                )
            }
            composable(route = Routes.Search.createRoute(),
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(100)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(100)
                    )
                }) {
                appState.setVisibleBottomNav(true)
                SearchScreen(
                    navBack = { navController.popBackStack() }
                )
            }
            composable(route = Routes.AllMakeUp.createRoute(),
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
                AllMakeUpScreen(
                    navBack = { navController.popBackStack() }
                )
            }
        }
    }
}