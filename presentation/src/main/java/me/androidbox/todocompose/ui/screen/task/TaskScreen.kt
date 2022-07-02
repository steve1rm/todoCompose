package me.androidbox.todocompose.ui.screen.task

import android.content.Context
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import me.androidbox.todocompose.model.TodoTask
import me.androidbox.todocompose.util.Action
import me.androidbox.todocompose.viewmodel.ShareViewModel

@Composable
fun TaskScreen(navigateToListScreen: (Action) -> Unit,
               shareViewModel: ShareViewModel,
               selectedTaskEntity: TodoTask?) {

    val title by shareViewModel.title
    val description by shareViewModel.description
    val priority by shareViewModel.priority
    val context = LocalContext.current

    BackHandler(onBackPressed = { navigateToListScreen(Action.NO_ACTION) })

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

@Composable
fun BackHandler(
    backPressedDispatcher: OnBackPressedDispatcher? = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
    onBackPressed: () -> Unit
) {
    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)

    val onBackPressedCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }

    DisposableEffect(key1 = backPressedDispatcher, effect = {
        backPressedDispatcher?.addCallback(onBackPressedCallback)

        onDispose {
            onBackPressedCallback.remove()
        }
    })
}
