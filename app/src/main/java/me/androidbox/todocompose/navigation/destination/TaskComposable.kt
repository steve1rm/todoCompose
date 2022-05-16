package me.androidbox.todocompose.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import me.androidbox.todocompose.Constant.TASK_ARGUMENT_KEY
import me.androidbox.todocompose.Constant.TASK_SCREEN
import me.androidbox.todocompose.util.Action

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { }
}