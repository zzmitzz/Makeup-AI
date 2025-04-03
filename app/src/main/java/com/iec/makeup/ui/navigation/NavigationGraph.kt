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
import com.iec.makeup.ui.features.ai_makeup.InstructionScreen
import com.iec.makeup.ui.features.ai_makeup.VirtualScreen
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
            composable(
                route = Routes.Login.createRoute(),
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
        // For type-safety, since the project is current in development then we will not implement this
        navigation(
            startDestination = Routes.MainHome.createRoute(),
            route = "main"
        ) {
            /*
              - Main Route
             */
            composable(route = Routes.MainHome.createRoute()) {
                appState.setVisibleBottomNav(true)
                HomeScreen(
                    navToNotification = {
                        navController.navigate(Routes.MainNotification.createRoute()) {
                            launchSingleTop = true
                            restoreState = false
                        }
                    },
                    navToSearch = {
                        navController.navigate(Routes.MainSearch.createRoute()) {
                            launchSingleTop = true
                            restoreState = false
                        }
                    },
                    navToAllMakeUp = {
                        navController.navigate(Routes.MainAllMakeUp.createRoute()) {
                            launchSingleTop = true
                            restoreState = false
                        }
                    }
                )
            }
            composable(
                route = Routes.MainNotification.createRoute(),
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
            composable(
                route = Routes.MainSearch.createRoute(),
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
            composable(
                route = Routes.MainAllMakeUp.createRoute(),
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


            /*
             - AI_Screen Route
             */

            navigation(
                startDestination = Routes.Page2.createRoute(),
                route = "ai"
            ) {
                composable(route = Routes.Page2.createRoute()) {
                    appState.setVisibleBottomNav(true)
                    VirtualScreen(
                        navBack = { navController.popBackStack() },
                        navInstruction = { navController.navigate(Routes.InstructionScreen.createRoute()) }
                    )
                }

                composable(route = Routes.InstructionScreen.createRoute()) {
                    appState.setVisibleBottomNav(false)
                    InstructionScreen(
                        navBack = { navController.popBackStack() },
                        navLaunchScreen = {
                            navController.navigate(Routes.Page2.createRoute()) {
                                popUpTo("ai") {
                                    inclusive = false
                                }
                                launchSingleTop = true
                                restoreState = false
                            }
                        }
                    )
                }

            }


            /*
             - Cart Route
             */
            composable(route = Routes.Page3.createRoute()) {
                appState.setVisibleBottomNav(true)
                Text("Page 3")
            }


            /*
             - Profile Route
             */
            composable(route = Routes.Page4.createRoute()) {
                appState.setVisibleBottomNav(true)
                Text("Page 4")
            }
        }
    }
}