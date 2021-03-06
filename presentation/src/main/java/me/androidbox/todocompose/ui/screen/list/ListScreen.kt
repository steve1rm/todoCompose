package me.androidbox.todocompose.ui.screen.list

import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch
import me.androidbox.todocompose.R
import me.androidbox.todocompose.ui.theme.fabBackgroundColor
import me.androidbox.todocompose.util.Action
import me.androidbox.todocompose.util.SearchAppBarState
import me.androidbox.todocompose.viewmodel.ShareViewModel

@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    shareViewModel: ShareViewModel,
    action: Action
) {

    LaunchedEffect(key1 = action, block = {
        shareViewModel.handleDatabaseAction(action)
    })

    val searchAppBarState: SearchAppBarState by shareViewModel.searchAppBarState
    val searchTextState: String by shareViewModel.searchTextState
    val listAllTask by shareViewModel.listOfTaskStateFlow.collectAsState()
    val searchAllTask by shareViewModel.searchTaskStateFlow.collectAsState()
    val listOfLowPriorityTask by shareViewModel.sortByLowPriorityStateFlow.collectAsState()
    val listOfHighPriorityTask by shareViewModel.sortByHighPriorityStateFlow.collectAsState()
    val sortState by shareViewModel.sortStateFlow.collectAsState()

    val scaffoldState = rememberScaffoldState()

    DisplaySnackBar(
        scaffoldState = scaffoldState,
        onComplete = {
            shareViewModel.actionMutableState.value = it
        },
        taskTitle = shareViewModel.title.value,
        action = action,
        onUndoClicked = { action ->
            shareViewModel.actionMutableState.value = action
    })

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListAppBar(
                shareViewModel = shareViewModel,
                searchAppBarState = searchAppBarState,
                searchText = searchTextState)
        },
        content = {
            ListContent(
                listAllTask,
                searchAllTask,
                listOfLowPriorityTask,
                listOfHighPriorityTask,
                sortState,
                searchAppBarState,
                navigateToTaskScreen,
                onSwipeToDelete = { todoTask, action ->
                    shareViewModel.actionMutableState.value = action
                    shareViewModel.updateSelectedTask(todoTask)
                })
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(onFabClicked: (taskId: Int) -> Unit) {
    FloatingActionButton(onClick = {
        onFabClicked(-1)
    },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_task_button),
            tint = Color.White)
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    onComplete: (Action) -> Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action
) {

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = action, block = {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = setMessageLabel(action, taskTitle),
                    actionLabel = setActionLabel(action))

                undoDeletedTask(
                    action,
                    snackBarResult,
                    onUndoClicked)
            }
        }

        onComplete(Action.NO_ACTION)
    })
}

private fun setMessageLabel(action: Action, taskTitle: String): String {
    return when (action) {
        Action.DELETE_ALL -> "All Task Removed"
        else -> "${action.name}: $taskTitle"
    }
}

private fun setActionLabel(action: Action): String {
    return if(action.name == "DELETE") {
        "UNDO"
    }
    else {
        "OK"
    }
}

private fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if(snackBarResult == SnackbarResult.ActionPerformed && action == Action.DELETE) {
        onUndoClicked(Action.UNDO)
    }
}
