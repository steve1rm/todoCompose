package me.androidbox.todocompose.ui.screen.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.todocompose.ui.theme.*
import me.androidbox.todocompose.util.RequestState

@Composable
fun ListContent(requestState: RequestState<List<TodoTaskEntity>>, navigateToTaskScreen: (taskId: Int) -> Unit) {

    if(requestState is RequestState.Success) {
        if (requestState.data.isNotEmpty()) {
            DisplayTask(
                listOfTodoTask = requestState.data,
                navigateToTaskScreen = navigateToTaskScreen
            )
        } else {
            EmptyContent()
        }
    }
}

@Composable
fun DisplayTask(listOfTodoTask: List<TodoTaskEntity>, navigateToTaskScreen: (taskId: Int) -> Unit) {
    LazyColumn {
        items(
            items = listOfTodoTask,
            key = { task ->
                task.id
            }) { todoTaskEntity ->
            TaskItem(
                todoTaskEntity = todoTaskEntity,
                navigateToTaskScreen = navigateToTaskScreen)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskItem(
    todoTaskEntity: TodoTaskEntity,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.taskItemBackgroundColor,
        shape = RectangleShape,
        elevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToTaskScreen(todoTaskEntity.id)
        }
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(all = LARGE_PADDING)) {
            Row {
                Text(
                    modifier = Modifier.weight(8F),
                    text = todoTaskEntity.title,
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
                        drawCircle(color = Color.Blue)
                    }
                }
            }
            Row {
                Text(
                    modifier = Modifier.weight(8F),
                    text = todoTaskEntity.description,
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
fun TaskItemPreview() {
    TaskItem(
        todoTaskEntity = TodoTaskEntity(
            id = 1,
            "This is the title",
            "This is the description and is displaying in the compose preview.",
            1
        ), navigateToTaskScreen = {}
    )
}