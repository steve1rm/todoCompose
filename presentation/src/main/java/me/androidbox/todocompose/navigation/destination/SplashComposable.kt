package me.androidbox.todocompose.navigation.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import me.androidbox.todocompose.Constant
import me.androidbox.todocompose.ui.screen.splash.SplashScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {
    composable(
        route = Constant.SPLASH_SCREEN) {
        SplashScreen(navigateToListScreen)
    }
}