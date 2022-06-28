package me.androidbox.todocompose.ui.screen.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.androidbox.todocompose.R
import me.androidbox.todocompose.model.Priority
import me.androidbox.todocompose.model.TodoTask
import me.androidbox.todocompose.ui.theme.*
import me.androidbox.todocompose.util.Action
import me.androidbox.todocompose.util.RequestState
import me.androidbox.todocompose.util.SearchAppBarState

@Composable
fun ListContent(
    listOfTask: RequestState<List<TodoTask>>,
    listOfSearchTask: RequestState<List<TodoTask>>,
    listOfLowPriorityTask: List<TodoTask>,
    listOfHighPriorityTask: List<TodoTask>,
    sortState: RequestState<Priority>,
    searchAppBarState: SearchAppBarState,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    onSwipeToDelete: (TodoTask, Action) -> Unit) {

    if(sortState is RequestState.Success) {
        when {
            searchAppBarState == SearchAppBarState.TRIGGERED -> {
                if (listOfSearchTask is RequestState.Success) {
                    HandleListContent(
                        listOfTask = listOfSearchTask.data,
                        navigateToTaskScreen = navigateToTaskScreen,
                        onSwipeToDelete = onSwipeToDelete)
                }
            }

            sortState.data == Priority.NONE -> {
                if (listOfTask is RequestState.Success) {
                    HandleListContent(
                        listOfTask = listOfTask.data,
                        navigateToTaskScreen = navigateToTaskScreen,
                        onSwipeToDelete = onSwipeToDelete)
                }
            }

            sortState.data == Priority.LOW -> {
                if (listOfTask is RequestState.Success) {
                    HandleListContent(
                        listOfTask = listOfLowPriorityTask,
                        navigateToTaskScreen = navigateToTaskScreen,
                        onSwipeToDelete = onSwipeToDelete)
                }
            }

            sortState.data == Priority.HIGH -> {
                if (listOfTask is RequestState.Success) {
                    HandleListContent(
                        listOfTask = listOfHighPriorityTask,
                        navigateToTaskScreen = navigateToTaskScreen,
                        onSwipeToDelete = onSwipeToDelete)
                }
            }
        }
    }
}

@Composable
fun HandleListContent(
    listOfTask: List<TodoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    onSwipeToDelete: (TodoTask, Action) -> Unit) {

    if (listOfTask.isEmpty()) {
        EmptyContent()
    }
    else {
        DisplayTask(
            listOfTodoTask = listOfTask,
            navigateToTaskScreen = navigateToTaskScreen,
            onSwipeToDelete = onSwipeToDelete
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DisplayTask(
    listOfTodoTask: List<TodoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    onSwipeToDelete: (TodoTask, Action) -> Unit
) {
    LazyColumn {
        items(
            items = listOfTodoTask,
            key = { task ->
                task.id
            }) { todoTask ->
            val dismissState = rememberDismissState()
            val dismissDirection = dismissState.dismissDirection
            val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)

            if(isDismissed && dismissDirection == DismissDirection.EndToStart) {
                val coroutineScope = rememberCoroutineScope()
                coroutineScope.launch {
                    delay(300)
                    onSwipeToDelete(todoTask, Action.DELETE)
                }
            }

            val degree by animateFloatAsState(
                if(dismissState.targetValue == DismissValue.Default)
                    0f
                else
                    -45F
            )

            var hasAppeared by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = true, block = {
                hasAppeared = true
            })

            AnimatedVisibility(
                visible = hasAppeared && !isDismissed,
                enter = expandVertically(animationSpec = tween(300)),
                exit = shrinkVertically(animationSpec = tween(300))) {
                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = { FractionalThreshold(fraction = 0.2f) },
                    background = { RedBackground(degree = degree) },
                    dismissContent = {
                        TaskItem(
                            todoTask = todoTask,
                            navigateToTaskScreen = navigateToTaskScreen)
                    })
            }
        }
    }
}

@Composable
fun RedBackground(degree: Float) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(HighPriorityColor)
        .padding(horizontal = LARGEST_PADDING),
        contentAlignment = Alignment.CenterEnd) {

        Icon(
            modifier = Modifier.rotate(degree),
            contentDescription = stringResource(id = R.string.delete_task),
            imageVector = Icons.Filled.Delete,
            tint = Color.White
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskItem(
    todoTask: TodoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.taskItemBackgroundColor,
        shape = RectangleShape,
        elevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToTaskScreen(todoTask.id)
        }
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(all = LARGE_PADDING)) {
            Row {
                Text(
                    modifier = Modifier.weight(8F),
                    text = todoTask.title,
                    color = MaterialTheme.colors.taskItemTextColor,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.CenterEnd) {
                    Canvas(modifier = Modifier
                        .size(PRIORITY_INDICATOR_SIZE)) {
                        drawCircle(color = todoTask.priority.color)
                    }
                }
            }
            Row {
                Text(
                    modifier = Modifier.weight(8F),
                    text = todoTask.description,
                    color = MaterialTheme.colors.taskItemTextColor,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
@Preview
private fun TaskItemPreview() {
    TaskItem(
        todoTask = TodoTask(
            id = 1,
            "This is the title",
            "This is the description and is displaying in the compose preview.",
            Priority.LOW
        ), navigateToTaskScreen = {}
    )
}


@Composable
@Preview
private fun RedBackgroundPreview() {
    Column(modifier = Modifier.height(100.dp)) {
        RedBackground(degree = -045f)
    }
}