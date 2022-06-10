package me.androidbox.todocompose.ui.screen.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import me.androidbox.todocompose.model.Priority
import me.androidbox.todocompose.R
import me.androidbox.todocompose.component.PriorityItem
import me.androidbox.todocompose.ui.theme.LARGE_PADDING
import me.androidbox.todocompose.ui.theme.TOP_APP_BAR_HEIGHT
import me.androidbox.todocompose.ui.theme.topAppBarBackgroundColor
import me.androidbox.todocompose.ui.theme.topAppBarContentColor
import me.androidbox.todocompose.util.SearchAppBarState
import me.androidbox.todocompose.util.TrailingIconState
import me.androidbox.todocompose.viewmodel.ShareViewModel

@Composable
fun ListAppBar(
    shareViewModel: ShareViewModel,
    searchAppBarState: SearchAppBarState,
    searchText: String
) {
    when(searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                onSearchBarClicked = {
                    shareViewModel.updateSearchAppBarState(SearchAppBarState.OPENED)
                },
                onSortClicked = {

                },
                onDeleteClicked = {

                })
        }
        else -> {
            SearchBarApp(
                text = searchText,
                onTextChange = { newText ->
                    shareViewModel.updateSearchText(newText)
                } ,
                onSearchClicked = {

                },
                onCloseClicked = {
                    shareViewModel.updateSearchAppBarState(SearchAppBarState.CLOSED)
                    shareViewModel.updateSearchText("")
                })
        }
    }
}

@Composable
fun DefaultListAppBar(onSearchBarClicked: () -> Unit, onSortClicked: (Priority) -> Unit, onDeleteClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.title),
            color = MaterialTheme.colors.topAppBarContentColor)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
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
        Icon(painter = painterResource(id = R.drawable.ic_overflow),
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
fun SearchBarApp(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    var trailingIconState by remember {
        mutableStateOf(TrailingIconState.READY_TO_DELETE)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { textChange -> onTextChange(textChange) },
            placeholder = { Text(modifier = Modifier.alpha(ContentAlpha.disabled), text = "Search", color = Color.White) },
            textStyle = TextStyle(
                color = MaterialTheme.colors.topAppBarContentColor,
                fontStyle = MaterialTheme.typography.subtitle1.fontStyle),
            singleLine = true,
            leadingIcon = { IconButton(modifier = Modifier.alpha(ContentAlpha.disabled), onClick = {  }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search for task",
                    tint = MaterialTheme.colors.topAppBarContentColor)
            }},
            trailingIcon = { IconButton(onClick = {
                when(trailingIconState) {
                    TrailingIconState.READY_TO_DELETE -> {
                        onTextChange("")
                        trailingIconState = TrailingIconState.READY_TO_CLOSE
                    }
                    TrailingIconState.READY_TO_CLOSE -> {
                        if(text.isNotEmpty()) {
                            onTextChange("")
                        }
                        else {
                            onCloseClicked()
                            trailingIconState = TrailingIconState.READY_TO_DELETE
                        }
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Search for task",
                    tint = MaterialTheme.colors.topAppBarContentColor)
            }},
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions {
                onSearchClicked(text)
            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.topAppBarContentColor,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        )
    }
}

@Composable
@Preview
fun DefaultListAppBarPreview() {
    DefaultListAppBar(onSearchBarClicked = {}, onSortClicked = {}, onDeleteClicked = {})
}

@Composable
@Preview
fun SearchBarAppPreview() {
    SearchBarApp(text = "", onTextChange = {} , onSearchClicked = {}, onCloseClicked = {})
}