package com.iec.makeup.ui.navigation

sealed class Routes(
    route: String
) {
    // Authorise Routes
    data object Login : Routes("login") {
        fun createRoute() = "login"
    }
    data object Register : Routes("register") {
        fun createRoute() = "register"
    }

    // Home routes
    data object Page1 : Routes("page1") {
        fun createRoute() = "page1"
    }
    data object Page2 : Routes("page2") {
        fun createRoute() = "page2"
        }
    data object Page3 : Routes("page3") {
        fun createRoute() = "page3"
    }
    data object Page4 : Routes("page4") {
        fun createRoute() = "page4"
    }


    companion object {
//        const val
    }
}


