package me.androidbox.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import me.androidbox.todocompose.Constant.SPLASH_SCREEN
import me.androidbox.todocompose.navigation.destination.listComposable
import me.androidbox.todocompose.navigation.destination.splashComposable
import me.androidbox.todocompose.navigation.destination.taskComposable
import me.androidbox.todocompose.viewmodel.ShareViewModel

@Composable
fun Navigation(
    navHostController: NavHostController,
    shareViewModel: ShareViewModel) {

    val screen = remember(navHostController) {
        Screen(navHostController = navHostController)
    }

    NavHost(
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