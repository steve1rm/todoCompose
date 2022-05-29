package me.androidbox.todocompose.ui.screen.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import me.androidbox.data.model.Priority
import me.androidbox.todocompose.R
import me.androidbox.todocompose.component.PriorityDropDown
import me.androidbox.todocompose.ui.theme.LARGE_PADDING
import me.androidbox.todocompose.ui.theme.MEDIUM_PADDING

@Composable
fun TaskContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colors.background)
        .padding(all = LARGE_PADDING)) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { _title ->
                onTitleChange(_title)
            },
            label = {
                Text(text = stringResource(R.string.task_title))
            },
            textStyle = MaterialTheme.typography.body1,
            singleLine = true)

        Spacer(
            modifier = Modifier.padding(MEDIUM_PADDING))

        PriorityDropDown(
            priority = priority,
            onPrioritySelected = onPrioritySelected)

        Spacer(
            modifier = Modifier.padding(MEDIUM_PADDING))

        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description,
            onValueChange = { _description ->
                onDescriptionChange(_description)
            },
            label = {
                Text(text = stringResource(R.string.task_description))
            },
            textStyle = MaterialTheme.typography.body1)
    }
}

@Composable
@Preview
fun TaskContentPreview() {
    TaskContent(
        title = "title",
        onTitleChange = {},
        description = "description",
        onDescriptionChange = {},
        priority = Priority.HIGH,
        onPrioritySelected = {}
    )
}