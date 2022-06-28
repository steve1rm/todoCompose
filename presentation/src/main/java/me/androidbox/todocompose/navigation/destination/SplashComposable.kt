package me.androidbox.todocompose.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import me.androidbox.todocompose.Constant
import me.androidbox.todocompose.ui.screen.splash.SplashScreen

fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {
    composable(
        route = Constant.SPLASH_SCREEN) {
        SplashScreen(navigateToListScreen)
    }
}