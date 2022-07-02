package me.androidbox.todocompose.navigation.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import me.androidbox.todocompose.Constant.TASK_ARGUMENT_KEY
import me.androidbox.todocompose.Constant.TASK_SCREEN
import me.androidbox.todocompose.ui.screen.task.TaskScreen
import me.androidbox.todocompose.util.Action
import me.androidbox.todocompose.viewmodel.ShareViewModel

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit,
    shareViewModel: ShareViewModel
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments?.getInt(TASK_ARGUMENT_KEY) ?: -1

        LaunchedEffect(key1 = taskId, block = {
            shareViewModel.getSelectedTask(taskId)
        })

        val selectedTask by shareViewModel.selectedTaskStateFlow.collectAsState()

        LaunchedEffect(key1 = selectedTask, block = {
            if(selectedTask != null || taskId == -1) {
                shareViewModel.updateSelectedTask(selectedTask)
            }
        })

        TaskScreen(
            navigateToListScreen = navigateToListScreen,
            shareViewModel = shareViewModel,
            selectedTaskEntity = selectedTask)
    }
}