package me.androidbox.todocompose.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.androidbox.todocompose.model.Priority
import me.androidbox.todocompose.R
import me.androidbox.todocompose.ui.theme.PRIORITY_DROP_DOWN_HEIGHT
import me.androidbox.todocompose.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    val angle: Float by animateFloatAsState(
        targetValue = if (isExpanded) 180F else 0F)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .height(PRIORITY_DROP_DOWN_HEIGHT)
            .clickable { isExpanded = true }
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                shape = MaterialTheme.shapes.small
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier
                .size(PRIORITY_INDICATOR_SIZE)
                .weight(1F)) {
            drawCircle(color = priority.color)
        }

        Text(
            modifier = Modifier.weight(8F),
            text = priority.name,
            style = MaterialTheme.typography.subtitle1
        )

        IconButton(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .rotate(angle)
                .weight(1F),
            onClick = { isExpanded = true }) {

            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = stringResource(R.string.drop_down_arrow_icon)
            )
        }

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(fraction = 0.94F),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(onClick = {
                isExpanded = false
                onPrioritySelected(Priority.LOW)
            }) {
                PriorityItem(priority = Priority.LOW)
            }

            DropdownMenuItem(onClick = {
                isExpanded = false
                onPrioritySelected(Priority.MEDIUM)
            }) {
                PriorityItem(priority = Priority.MEDIUM)
            }

            DropdownMenuItem(onClick = {
                isExpanded = false
                onPrioritySelected(Priority.HIGH)
            }) {
                PriorityItem(priority = Priority.HIGH)
            }
        }
    }
}

@Composable
@Preview
fun PriorityDropDownPreview() {
    PriorityDropDown(priority = Priority.MEDIUM, onPrioritySelected = {})
}