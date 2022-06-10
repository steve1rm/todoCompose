package me.androidbox.todocompose.model

data class TodoTask(
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority)
