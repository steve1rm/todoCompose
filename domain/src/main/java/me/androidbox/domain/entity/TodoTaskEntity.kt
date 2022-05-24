package me.androidbox.domain.entity


data class TodoTaskEntity(
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Int
)