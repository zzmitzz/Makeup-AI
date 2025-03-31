package com.iec.makeup.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.iec.makeup.ui.navigation.BottomNavigationBar
import com.iec.makeup.ui.navigation.NavigationGraph


const val TAG = "MakeupApp"


@Composable
fun MakeupApp(
    navController: NavHostController,
    appState: MakeupAppState
) {

    val isShowBottomNav = appState.isShownBottomNav.collectAsStateWithLifecycle()
    val currDestination = appState.currentTopLevelDestination.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        Log.d(TAG, "Trigger Recompose")
    }


    return ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        // Create references for the composables to constrain
        val (nav, bottomBar) = createRefs()

        // NavigationGraph
        Box(
            modifier = Modifier
                .constrainAs(nav) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(if (isShowBottomNav.value) bottomBar.top else parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        ) {
            NavigationGraph(navController = navController, appState = appState)
        }

        // BottomNavigationBar (conditionally shown)
        if (isShowBottomNav.value) {
            Box(
                modifier = Modifier
                    .constrainAs(bottomBar) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
            ) {
                BottomNavigationBar(
                    onTopLevelClick = {
                        appState.navigateToTopLevelDestination(it)
                    },
                    currentDestination = currDestination.value
                )
            }
        }
    }

}