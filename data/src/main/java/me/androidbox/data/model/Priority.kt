package me.androidbox.data.model

import androidx.compose.ui.graphics.Color
import me.androidbox.todocompose.ui.theme.HighPriorityColor
import me.androidbox.todocompose.ui.theme.LowPriorityColor
import me.androidbox.todocompose.ui.theme.MediumPriorityColor
import me.androidbox.todocompose.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}
