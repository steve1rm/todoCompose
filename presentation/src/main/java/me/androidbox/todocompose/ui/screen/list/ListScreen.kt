package me.androidbox.todocompose.ui.screen.list

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import me.androidbox.todocompose.R
import me.androidbox.todocompose.ui.theme.fabBackgroundColor
import me.androidbox.todocompose.util.SearchAppBarState
import me.androidbox.todocompose.viewmodel.ShareViewModel

@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    shareViewModel: ShareViewModel
) {
    val searchAppBarState: SearchAppBarState by shareViewModel.searchAppBarState
    val searchTextState: String by shareViewModel.searchTextState

    LaunchedEffect(key1 = true) {
        shareViewModel.getAllTasks()
    }

    val listAllTask by shareViewModel.listOfTaskStateFlow.collectAsState()

    Scaffold(
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
