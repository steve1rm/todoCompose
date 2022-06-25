package me.androidbox.todocompose.navigation.destination

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import me.androidbox.todocompose.Constant.LIST_ARGUMENT_KEY
import me.androidbox.todocompose.Constant.LIST_SCREEN
import me.androidbox.todocompose.ui.screen.list.ListScreen
import me.androidbox.todocompose.util.toAction
import me.androidbox.todocompose.viewmodel.ShareViewModel

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    shareViewModel: ShareViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()

        LaunchedEffect(key1 = action, block = {
            shareViewModel.actionMutableState.value = action
        })

        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            shareViewModel = shareViewModel)
    }
}