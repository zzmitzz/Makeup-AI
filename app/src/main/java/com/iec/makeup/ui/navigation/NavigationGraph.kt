package com.iec.makeup.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.iec.ui.feature.main.message.box_chat_message.ModernChatScreen
import com.iec.makeup.ui.MakeupAppState
import com.iec.makeup.ui.features.ai_makeup.InstructionScreen
import com.iec.makeup.ui.features.ai_makeup.VirtualScreen
import com.iec.makeup.ui.features.authentication.login.LoginScreen
import com.iec.makeup.ui.features.authentication.register.RegisterScreen
import com.iec.makeup.ui.features.home.HomeScreen
import com.iec.makeup.ui.features.home.screen_notification.NotificationContent
import com.iec.makeup.ui.features.home.screen_search.SearchScreen
import com.iec.makeup.ui.features.home.screen_all_makeup.AllMakeUpScreen
import com.iec.makeup.ui.features.home.screen_makeup_info.ProfileScreen


@Composable
fun NavigationGraph(
    navController: NavHostController,
    appState: MakeupAppState
) {
    NavHost(navController = navController, startDestination = "auth") {

        navigation(
            startDestination = Routes.Login.route,
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
            startDestination = Routes.MainHome.route,
            route = "main"
        ) {
            /*
              - Main Route
             */
            composable(route = Routes.MainHome.route) {
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
                    },
                    navToPersonalInfo = { id ->
                        navController.navigate(Routes.MainDetailMakeUp.createRoute(id)) {
                            launchSingleTop = true
                            restoreState = false
                        }
                    },
                    navToChatting = {
                        navController.navigate(Routes.MainChatting.createRoute("0")) {
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
                route = Routes.MainSearch.route,
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
                route = Routes.MainAllMakeUp.route,
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
                    navBack = { navController.popBackStack() },
                    navToDetail = {
                        navController.navigate(Routes.MainDetailMakeUp.createRoute(it))
                    }
                )
            }

            composable(
                route = Routes.MainDetailMakeUp.route,
                arguments = listOf(
                    navArgument(Routes.MakeUpStylistID) { type = NavType.StringType }
                )
            ) {
                val idMakeUp = it.arguments?.getString(Routes.MakeUpStylistID) ?: "0"
                ProfileScreen(
                    id = idMakeUp,
                    navBack = { navController.popBackStack() }
                )
            }

            composable(
                route = Routes.MainChatting.route,
                arguments = listOf(
                    navArgument(Routes.MakeUpStylistID) { type = NavType.StringType }
                )
            ) {
                appState.setVisibleBottomNav(false)
                val idMakeUp = it.arguments?.getString(Routes.MakeUpStylistID) ?: "0"
                ModernChatScreen(
                    userName = idMakeUp,
                    onBackPress = { navController.popBackStack() }
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