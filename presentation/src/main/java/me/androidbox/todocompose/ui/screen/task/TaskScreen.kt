package me.androidbox.todocompose.ui.screen.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.todocompose.util.Action
import me.androidbox.todocompose.viewmodel.ShareViewModel

@Composable
fun TaskScreen(navigateToListScreen: (Action) -> Unit,
               shareViewModel: ShareViewModel,
               selectedTaskEntity: TodoTaskEntity?) {

    val title by shareViewModel.title
    val description by shareViewModel.description
    val priority by shareViewModel.priority

    Scaffold(
        topBar = {
            TaskAppBar(
                navigateToListScreen = navigateToListScreen,
                selectedTask = selectedTaskEntity)
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = { title ->
                    shareViewModel.updateTitle(title)
                },
                description = description,
                onDescriptionChange = { description -> shareViewModel.description.value = description },
                priority = priority,
                onPrioritySelected = { priority -> shareViewModel.priority.value = priority }
            )
        })
}
