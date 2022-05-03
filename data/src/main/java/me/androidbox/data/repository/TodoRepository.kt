package me.androidbox.data.repository

import kotlinx.coroutines.flow.Flow
import me.androidbox.data.model.ToDoTaskModel

interface TodoRepository  {

    fun getAllTasks(): Flow<List<ToDoTaskModel>>

    fun getSelectedTask(taskId: Int): Flow<ToDoTaskModel>

    suspend fun addTask(toDoTaskModel: ToDoTaskModel)

    suspend fun updateTask(toDoTaskModel: ToDoTaskModel)

    suspend fun deleteTask(toDoTaskModel: ToDoTaskModel)

    suspend fun deleteAllTask()

    fun searchDatabase(searchQuery: String): Flow<List<ToDoTaskModel>>

    fun sortByLowPriority(): Flow<List<ToDoTaskModel>>

    fun sortByHighPriority(): Flow<List<ToDoTaskModel>>
}