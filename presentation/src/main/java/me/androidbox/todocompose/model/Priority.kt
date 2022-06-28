package me.androidbox.todocompose.model

import androidx.compose.ui.graphics.Color
import me.androidbox.todocompose.ui.theme.HighPriorityColor
import me.androidbox.todocompose.ui.theme.LowPriorityColor
import me.androidbox.todocompose.ui.theme.MediumPriorityColor
import me.androidbox.todocompose.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    NONE(NonePriorityColor),
    LOW(LowPriorityColor),
    MEDIUM(MediumPriorityColor),
    HIGH(HighPriorityColor)
}
