package com.iec.makeup.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.iec.makeup.R
import kotlin.reflect.KClass

/**
 * Type for the top level destinations in the application. Contains metadata about the destination
 * that is used in the top app bar and common navigation UI.
 *
 * @param selectedIcon The icon to be displayed in the navigation UI when this destination is
 * selected.
 * @param unselectedIcon The icon to be displayed in the navigation UI when this destination is
 * not selected.
 * @param iconTextId Text that to be displayed in the navigation UI.
 * @param titleTextId Text that is displayed on the top app bar.
 * @param route The route to use when navigating to this destination.
 */
enum class TopLevelDestination(
    val selectedIcon: Int,
    val unSelectedIcon: Int,
    @StringRes val iconText: Int,
    @StringRes val titleTextId: Int,
    val route: String
) {
    Page1(
        selectedIcon = R.drawable.home_24dp_df9d9b_fill1_wght400_grad0_opsz24,
        unSelectedIcon = R.drawable.home_24dp_df9d9b_fill0_wght400_grad0_opsz24,
        iconText = R.string.page1,
        titleTextId = R.string.page1_title,
        route = Routes.MainHome.createRoute()
    ),
    Page2(
        selectedIcon = R.drawable.lens_blur_24dp_df9d9b_fill0_wght400_grad0_opsz24,
        unSelectedIcon = R.drawable.lens_blur_24dp_df9d9b_fill0_wght400_grad0_opsz24,
        iconText = R.string.page2,
        titleTextId = R.string.page2_title,
        route = Routes.Page2.createRoute()
    ),
    Page3(
        selectedIcon = R.drawable.shopping_cart_24dp_df9d9b_fill1_wght400_grad0_opsz24,
        unSelectedIcon = R.drawable.shopping_cart_24dp_df9d9b_fill0_wght400_grad0_opsz24,
        iconText = R.string.page3,
        titleTextId = R.string.page3_title,
        route = Routes.Page3.createRoute()
    ),
    Page4(
        selectedIcon = R.drawable.account_circle_24dp_df9d9b_fill1_wght400_grad0_opsz24,
        unSelectedIcon = R.drawable.account_circle_24dp_df9d9b_fill0_wght400_grad0_opsz24,
        iconText = R.string.page4,
        titleTextId = R.string.page4_title,
        route = Routes.Page4.createRoute()
    )

}

// Just rush, not yet implement type safe for this navigation.

sealed class Routes(
    route: String
) {

    /*
    -- Route /auth --
     */
    // Authorise Routes
    data object Login : Routes("login") {
        fun createRoute() = "login"
    }

    data object Register : Routes("register") {
        fun createRoute() = "register"
    }

    /*
    -- Route /main/home --
     */
    data object MainHome : Routes("page1") {
        fun createRoute() = "main//page1"
    }

    data object MainNotification : Routes("notification") {
        fun createRoute() = "maidn/notification"
    }

    data object MainSearch : Routes("search") {
        fun createRoute() = "main/search"
    }

    data object MainAllMakeUp : Routes("all_makeup") {
        fun createRoute() = "main/all_makeup"
    }


    /*
    -- Route /main/ai --
     */
    data object Page2 : Routes("page2") {
        fun createRoute() = "main/ai/page2"
    }

    data object InstructionScreen : Routes("instruction") {
        fun createRoute() = "main/ai/instruction"
    }


    /*
    -- Route page3
     */
    data object Page3 : Routes("page3") {
        fun createRoute() = "main/page3"
    }

    data object Page4 : Routes("page4") {
        fun createRoute() = "main/page4"
    }

    companion object {
//        const val
    }
}