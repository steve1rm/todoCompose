package me.androidbox.todocompose.ui.screen.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import me.androidbox.data.model.Priority
import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.todocompose.util.Action
import me.androidbox.todocompose.viewmodel.ShareViewModel

@Composable
fun TaskScreen(navigateToListScreen: (Action) -> Unit,
               shareViewModel: ShareViewModel,
               selectedTaskEntity: TodoTaskEntity?) {
    Scaffold(
        topBar = {
            TaskAppBar(
                navigateToListScreen = navigateToListScreen,
                selectedTask = selectedTaskEntity)
        },
        content = {
            TaskContent(
                title = "title",
                onTitleChange = {},
                description = "description",
                onDescriptionChange = {},
                priority = Priority.HIGH,
                onPrioritySelected = {}
            )
        })
}
