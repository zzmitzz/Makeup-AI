package com.iec.makeup.ui.features.authentication

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kotlinx.serialization.Serializable


@Serializable object AuthenticationRoute

@Composable
fun AuthenticationNav(
    navToHome: () -> Unit,
    navToRegister: () -> Unit
){

}