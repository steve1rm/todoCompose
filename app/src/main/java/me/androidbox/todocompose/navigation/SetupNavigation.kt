package me.androidbox.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import me.androidbox.todocompose.Constant.LIST_SCREEN
import me.androidbox.todocompose.navigation.destination.listComposable
import me.androidbox.todocompose.navigation.destination.taskComposable

@Composable
fun navigation(navHostController: NavHostController) {
    val screen = remember(navHostController) {
        Screen(navHostController = navHostController)
    }

    NavHost(navController = navHostController, startDestination = LIST_SCREEN) {
        listComposable(
            navigateToTaskScreen = screen.task
        )

        taskComposable(
            navigateToListScreen = screen.list
        )
    }
}