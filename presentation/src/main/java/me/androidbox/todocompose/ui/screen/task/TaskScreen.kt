package me.androidbox.todocompose.ui.screen.task

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBar(
                navigateToListScreen = { action ->
                    if(action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    }
                    else {
                        if(shareViewModel.hasValidField()) {
                            navigateToListScreen(action)
                        }
                        else {
                            displayToastMessage(context)
                        }
                    }
                },
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

fun displayToastMessage(context: Context) {
    Toast.makeText(context, "There are invalid fields", Toast.LENGTH_LONG).show()
}
