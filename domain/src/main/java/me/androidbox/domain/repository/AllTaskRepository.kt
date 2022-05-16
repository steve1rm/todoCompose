package me.androidbox.domain.repository

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.entity.TodoTaskEntity

interface AllTaskRepository {
    fun getAllTasks(): Flow<List<TodoTaskEntity>>
}