package me.androidbox.todocompose.ui.screen.task

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import me.androidbox.todocompose.R
import me.androidbox.todocompose.component.DisplayAlertDialog
import me.androidbox.todocompose.model.Priority
import me.androidbox.todocompose.model.TodoTask
import me.androidbox.todocompose.ui.theme.topAppBarBackgroundColor
import me.androidbox.todocompose.ui.theme.topAppBarContentColor
import me.androidbox.todocompose.util.Action

@Composable
fun TaskAppBar(navigateToListScreen: (Action) -> Unit, selectedTask: TodoTask?) {
    if(selectedTask == null) {
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    }
    else {
        ExistingTaskAppBar(selectedTask = selectedTask, navigateToListScreen = navigateToListScreen)
    }
}

@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit) {

    TopAppBar(
        navigationIcon = {
            BackAction(onBackClicked = { navigateToListScreen(Action.NO_ACTION) })
        },
        title = {
            Text(
                text = stringResource(R.string.add_task),
                color = MaterialTheme.colors.topAppBarContentColor)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            AddAction(onAddClicked = navigateToListScreen)
        }
    )
}

@Composable
fun ExistingTaskAppBar(
    selectedTask: TodoTask,
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            CloseAction(onCloseClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = selectedTask.title,
                color = MaterialTheme.colors.topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            ExistingTaskBarAction(
                navigateToListScreen = navigateToListScreen,
                selectedTask = selectedTask
            )
        }
    )
}

@Composable
fun ExistingTaskBarAction(
    selectedTask: TodoTask,
    navigateToListScreen: (Action) -> Unit) {

    var shouldOpenAlertDialog by remember {
        mutableStateOf(false)
    }

    DisplayAlertDialog(
        title = stringResource(R.string.delete_task_title, selectedTask.title),
        message = stringResource(R.string.delete_task_message, selectedTask.title),
        shouldOpenDialog = shouldOpenAlertDialog,
        closeDialog = { shouldOpenAlertDialog = false },
        onYesClicked = { navigateToListScreen(Action.DELETE) }
    )

    DeleteAction(onDeleteClicked = {
        shouldOpenAlertDialog = true
    })

    UpdateAction(onUpdateClicked = {
        navigateToListScreen(it)
    })
}

@Composable
fun BackAction(onBackClicked: (Action) -> Unit) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back_button),
            tint = MaterialTheme.colors.topAppBarContentColor)
    }
}

@Composable
fun AddAction(onAddClicked: (Action) -> Unit) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(R.string.add_task),
            tint = MaterialTheme.colors.topAppBarContentColor)
    }
}

@Composable
fun CloseAction(onCloseClicked: (Action) -> Unit) {
    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(R.string.back_button),
            tint = MaterialTheme.colors.topAppBarContentColor)
    }
}

@Composable
fun DeleteAction(onDeleteClicked: () -> Unit) {
    IconButton(onClick = { onDeleteClicked() }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(R.string.delete_task),
            tint = MaterialTheme.colors.topAppBarContentColor)
    }
}

@Composable
fun UpdateAction(onUpdateClicked: (Action) -> Unit) {
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(R.string.update_task),
            tint = MaterialTheme.colors.topAppBarContentColor)
    }
}

@Composable
@Preview
fun TaskAppBarPreview() {
    NewTaskAppBar(navigateToListScreen = { })
}

@Composable
@Preview
fun ExistingAppBarPreview() {
    ExistingTaskAppBar(selectedTask = TodoTask(0, "This a single task", "This the task description", Priority.MEDIUM), navigateToListScreen = { })
}


