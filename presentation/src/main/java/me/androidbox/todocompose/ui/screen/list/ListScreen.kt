package me.androidbox.todocompose.ui.screen.list

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
    shareViewModel: ShareViewModel
) {
    LaunchedEffect(key1 = true) {
        shareViewModel.getAllTasks()
    }

    val searchAppBarState: SearchAppBarState by shareViewModel.searchAppBarState
    val searchTextState: String by shareViewModel.searchTextState
    val action: Action by shareViewModel.actionMutableState
    val listAllTask by shareViewModel.listOfTaskStateFlow.collectAsState()
    val scaffoldState = rememberScaffoldState()

    shareViewModel.handleDatabaseAction(action)

    DisplaySnackBar(
        scaffoldState = scaffoldState,
        handleDatabaseAction = {
            shareViewModel.handleDatabaseAction(action)
        }
        , taskTitle = shareViewModel.title.value
        , action = action)

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
                navigateToTaskScreen)
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
    handleDatabaseAction: () -> Unit,
    taskTitle: String,
    action: Action
) {

    handleDatabaseAction()

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = action, block = {
        if(action != Action.NO_ACTION) {
            scope.launch {
                val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = "${action.name}: $taskTitle",
                    actionLabel = "OK"
                )
            }
        }
    })
}
