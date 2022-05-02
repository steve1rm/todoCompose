package me.androidbox.data.repository.imp

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import me.androidbox.data.dao.ToDoDao
import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.domain.usecase.*
import javax.inject.Inject

@ViewModelScoped
class TodoRepositoryImp @Inject constructor(private val toDoDao: ToDoDao) :
    GetAllTaskUseCase,
    GetSelectedTaskUseCase,
    AddTaskUseCase,
    UpdateTaskUseCase,
    DeleteAllTaskUseCase,
    DeleteTaskUseCase,
    SearchDatabaseUseCase,
    SortByHighPriorityUseCase,
    SortByLowPriorityUseCase {

    override suspend fun addTask(todoTaskEntity: TodoTaskEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllTask() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(todoTaskEntity: TodoTaskEntity) {
        TODO("Not yet implemented")
    }

    override fun getAllTask(): Flow<List<TodoTaskEntity>> {
        TODO("Not yet implemented")
    }

    override fun getSelectedTask(taskId: Int): Flow<TodoTaskEntity> {
        TODO("Not yet implemented")
    }

    override fun searchDatabase(searchQuery: String): Flow<List<TodoTaskEntity>> {
        TODO("Not yet implemented")
    }

    override fun sortByHighPriority(): Flow<List<TodoTaskEntity>> {
        TODO("Not yet implemented")
    }

    override fun sortByLowPriority(): Flow<List<TodoTaskEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(todoTaskEntity: TodoTaskEntity) {
        TODO("Not yet implemented")
    }
}