package me.androidbox.data.repository

import kotlinx.coroutines.flow.Flow
import me.androidbox.data.model.ToDoTask

interface TodoRepository  {

    fun getAllTasks(): Flow<List<ToDoTask>>

    fun getSelectedTask(taskId: Int): Flow<ToDoTask>

    suspend fun addTask(toDoTask: ToDoTask)

    suspend fun updateTask(toDoTask: ToDoTask)

    suspend fun deleteTask(toDoTask: ToDoTask)

    suspend fun deleteAllTask()

    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>>

    fun sortByLowPriority(): Flow<List<ToDoTask>>

    fun sortByHighPriority(): Flow<List<ToDoTask>>
}