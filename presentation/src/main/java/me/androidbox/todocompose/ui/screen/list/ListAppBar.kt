package me.androidbox.todocompose.ui.screen.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import me.androidbox.data.model.Priority
import me.androidbox.todocompose.R
import me.androidbox.todocompose.component.PriorityItem
import me.androidbox.todocompose.ui.theme.LARGE_PADDING
import me.androidbox.todocompose.ui.theme.topAppBarBackground
import me.androidbox.todocompose.ui.theme.topAppBarContentColor

@Composable
fun ListAppBar() {
    DefaultListAppBar(onSearchBarClicked = {}, onSortClicked = {}, onDeleteClicked = {})
}

@Composable
fun DefaultListAppBar(onSearchBarClicked: () -> Unit, onSortClicked: (Priority) -> Unit, onDeleteClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.title),
            color = MaterialTheme.colors.topAppBarContentColor)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackground,
        actions = {
            ListAppBarActions(onSearchBarClicked, onSortClicked, onDeleteClicked)
        }
    )
}

@Composable
fun ListAppBarActions(onSearchBarClicked: () -> Unit, onSortClicked: (Priority) -> Unit, onDeleteClicked: () -> Unit) {
    SearchAction(onSearchBarClicked = onSearchBarClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAction(onDeleteClicked = onDeleteClicked)
}

@Composable
fun SearchAction(onSearchBarClicked: () -> Unit) {
    IconButton(
        onClick = {
            onSearchBarClicked()
        }
    ) {
        Icon(imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.search_for_tasks),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun SortAction(onSortClicked: (Priority) -> Unit) {
    var isExpanded by remember { mutableStateOf(false)}

    IconButton(onClick = { isExpanded = true } ) {
        Icon(painter =  painterResource(id = R.drawable.ic_filter_list),
        contentDescription = stringResource(R.string.sort_tasks),
        tint = MaterialTheme.colors.topAppBarContentColor)
    }

    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = {  } ) {

        DropdownMenuItem(onClick = {
            isExpanded = false
            onSortClicked(Priority.HIGH)
        }) {
            PriorityItem(priority = Priority.HIGH)
        }

        DropdownMenuItem(onClick = {
            isExpanded = false
            onSortClicked(Priority.MEDIUM)
        }) {
            PriorityItem(priority = Priority.MEDIUM)
        }

        DropdownMenuItem(onClick = {
            isExpanded = false
            onSortClicked(Priority.LOW)
        }) {
            PriorityItem(priority = Priority.LOW)
        }

        DropdownMenuItem(onClick = {
            isExpanded = false
            onSortClicked(Priority.NONE)
        }) {
            PriorityItem(priority = Priority.NONE)
        }
    }
}

@Composable
fun DeleteAction(onDeleteClicked: () -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    IconButton(onClick = { isExpanded = true } ) {
        Icon(painter = painterResource(id = R.drawable.ic_delete),
            contentDescription = stringResource(R.string.delete_all_tasks),
            tint = MaterialTheme.colors.topAppBarContentColor)
    }

    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = {  }) {

        DropdownMenuItem(onClick = {
            onDeleteClicked()
            isExpanded = false
        }) {
            Text(
                modifier = Modifier.padding(LARGE_PADDING),
                text = stringResource(id = R.string.delete_all_tasks),
                style = MaterialTheme.typography.subtitle1)
        }
    }
}

@Composable
@Preview
fun DefaultListAppBarPreview() {
    DefaultListAppBar(onSearchBarClicked = {}, onSortClicked = {}, onDeleteClicked = {})
}