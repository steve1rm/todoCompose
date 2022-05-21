package me.androidbox.todocompose.ui.screen.list

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import me.androidbox.todocompose.R
import me.androidbox.todocompose.ui.theme.topAppBarBackground
import me.androidbox.todocompose.ui.theme.topAppBarContentColor

@Composable
fun ListAppBar() {
    DefaultListAppBar(onSearchBarClicked = { })
}

@Composable
fun DefaultListAppBar(onSearchBarClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "Todo Tasks",
            color = MaterialTheme.colors.topAppBarContentColor)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackground,
        actions = {
            ListAppBarActions(onSearchBarClicked)
        }
    )
}

@Composable
fun ListAppBarActions(onSearchBarClicked: () -> Unit) {
    SearchAction(onSearchBarClicked)
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
@Preview
fun DefaultListAppBarPreview() {
    DefaultListAppBar(onSearchBarClicked = {})
}