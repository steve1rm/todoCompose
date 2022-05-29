package me.androidbox.todocompose.navigation

import androidx.navigation.NavHostController
import me.androidbox.todocompose.Constant.LIST_SCREEN
import me.androidbox.todocompose.Constant.TASK_SCREEN
import me.androidbox.todocompose.util.Action

class Screen(navHostController: NavHostController) {
    val list: (action: Action) -> Unit = { action ->
        navHostController.navigate(route = "list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true}
        }
    }

    val task: (taskId: Int) -> Unit = { taskId ->
        navHostController.navigate(route = "task/$taskId")
    }
}