package me.androidbox.todocompose.navigation

import androidx.navigation.NavHostController
import me.androidbox.todocompose.Constant.LIST_SCREEN
import me.androidbox.todocompose.Constant.TASK_SCREEN
import me.androidbox.todocompose.util.Action

class Screen(navHostController: NavHostController) {
    val list: (Action) -> Unit = { action ->
        navHostController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true}
        }
    }

    val task: (Int) -> Unit = { taskId ->
        navHostController.navigate("task/$taskId")
    }
}