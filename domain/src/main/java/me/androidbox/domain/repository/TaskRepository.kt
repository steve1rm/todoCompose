package me.androidbox.domain.repository

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.entity.TodoTaskEntity

interface TaskRepository {
    fun fetchAllTask(): Flow<List<TodoTaskEntity>>
    suspend fun addTask(todoTaskEntity: TodoTaskEntity)
    suspend fun deleteAllTask()
    suspend fun deleteTask(todoTaskEntity: TodoTaskEntity)
    fun fetchSelectedTask(taskId: Int): Flow<TodoTaskEntity>
    fun searchDatabase(searchQuery: String): Flow<List<TodoTaskEntity>>
    fun sortByHighPriority(): Flow<List<TodoTaskEntity>>
    fun sortByLowPriority(): Flow<List<TodoTaskEntity>>
    suspend fun updateTask(todoTaskEntity: TodoTaskEntity)
}