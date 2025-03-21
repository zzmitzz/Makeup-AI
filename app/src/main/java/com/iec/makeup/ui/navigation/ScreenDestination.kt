package com.iec.makeup.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.iec.makeup.R

enum class ScreenDestination(
    val selectedIcon: Int,
    val unSelectedIcon: Int,
    @StringRes val iconText: Int,
    @StringRes val titleTextId: Int,
    val route: String
) {
    Page1(
        selectedIcon = R.drawable.check_circle_24dp_df9d9b_fill1_wght400_grad0_opsz24,
        unSelectedIcon = R.drawable.circle_24dp_434343_fill0_wght400_grad0_opsz24,
        iconText = R.string.page1,
        titleTextId = R.string.page1_title,
        route = Routes.Page1.createRoute()
    ),
    Page2(
        selectedIcon = R.drawable.check_circle_24dp_df9d9b_fill1_wght400_grad0_opsz24,
        unSelectedIcon = R.drawable.circle_24dp_434343_fill0_wght400_grad0_opsz24,
        iconText = R.string.page2,
        titleTextId = R.string.page2_title,
        route = Routes.Page2.createRoute()
    ),
    Page3(
        selectedIcon = R.drawable.check_circle_24dp_df9d9b_fill1_wght400_grad0_opsz24,
        unSelectedIcon = R.drawable.circle_24dp_434343_fill0_wght400_grad0_opsz24,
        iconText = R.string.page3,
        titleTextId = R.string.page3_title,
        route = Routes.Page3.createRoute()
    ),
    Page4(
        selectedIcon = R.drawable.check_circle_24dp_df9d9b_fill1_wght400_grad0_opsz24,
        unSelectedIcon = R.drawable.circle_24dp_434343_fill0_wght400_grad0_opsz24,
        iconText = R.string.page4,
        titleTextId = R.string.page4_title,
        route = Routes.Page4.createRoute()
    )

}