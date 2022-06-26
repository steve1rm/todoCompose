package me.androidbox.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import me.androidbox.data.model.ToDoTaskModel

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTasks(): Flow<List<ToDoTaskModel>>

    @Query("SELECT * FROM todo_table WHERE id=:taskId")
    fun getSelectedTask(taskId: Int): Flow<ToDoTaskModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(toDoTaskModel: ToDoTaskModel)

    @Update
    suspend fun updateTask(toDoTaskModel: ToDoTaskModel)

    @Delete
    suspend fun deleteTask(toDoTaskModel: ToDoTaskModel)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllTask()

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<ToDoTaskModel>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE '3%' THEN 1 WHEN priority LIKE '2%' THEN 2 WHEN priority LIKE '0%' THEN 3 END")
    fun sortByLowPriority(): Flow<List<ToDoTaskModel>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE '0%' THEN 1 WHEN priority LIKE '2%' THEN 2 WHEN priority LIKE '3%' THEN 3 END")
    fun sortByHighPriority(): Flow<List<ToDoTaskModel>>
}
