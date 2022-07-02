package me.androidbox.todocompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import me.androidbox.todocompose.Constant.SPLASH_SCREEN
import me.androidbox.todocompose.navigation.destination.listComposable
import me.androidbox.todocompose.navigation.destination.splashComposable
import me.androidbox.todocompose.navigation.destination.taskComposable
import me.androidbox.todocompose.viewmodel.ShareViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    navHostController: NavHostController,
    shareViewModel: ShareViewModel) {

    val screen = remember(navHostController) {
        Screen(navHostController = navHostController)
    }

    AnimatedNavHost(
        navController = navHostController,
        startDestination = SPLASH_SCREEN) {
        splashComposable(screen.splash)

        listComposable(
            navigateToTaskScreen = screen.list,
            shareViewModel = shareViewModel
        )

        taskComposable(
            navigateToListScreen = screen.task,
            shareViewModel = shareViewModel
        )
    }
}