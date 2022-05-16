package me.androidbox.data.model

/*
import me.androidbox.todocompose.ui.theme.HighPriorityColor
import me.androidbox.todocompose.ui.theme.LowPriorityColor
import me.androidbox.todocompose.ui.theme.MediumPriorityColor
import me.androidbox.todocompose.ui.theme.NonePriorityColor
*/

enum class Priority(val color: Int) {
    HIGH(1),
    MEDIUM(2),
    LOW(3),
    NONE(4)
}
