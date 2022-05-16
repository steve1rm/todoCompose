package me.androidbox.data.repository.imp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.androidbox.data.dao.ToDoDao
import me.androidbox.data.mapper.DataToDomainMapper
import me.androidbox.data.mapper.DomainToDataMapper
import me.androidbox.data.model.ToDoTaskModel
import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.domain.repository.TaskRepository
import javax.inject.Inject

class TodoRepositoryImp @Inject constructor(
    private val toDoDao: ToDoDao,
    private val dataToDomainMapper: DataToDomainMapper<@JvmSuppressWildcards ToDoTaskModel, @JvmSuppressWildcards TodoTaskEntity>,
    private val domainToDataMapper: DomainToDataMapper<@JvmSuppressWildcards TodoTaskEntity, @JvmSuppressWildcards ToDoTaskModel>)
    : TaskRepository {

    override fun fetchAllTask(): Flow<List<TodoTaskEntity>> {
        return toDoDao.getAllTasks()
            .map { listOfTodoTaskModel ->
                listOfTodoTaskModel.map { toDoTaskModel ->
                    dataToDomainMapper.map(toDoTaskModel)
                }
            }
    }

    override suspend fun addTask(todoTaskEntity: TodoTaskEntity) {
        val todoTaskModel = domainToDataMapper.map(todoTaskEntity)

        toDoDao.addTask(todoTaskModel)
    }

    override suspend fun deleteAllTask() {
        toDoDao.deleteAllTask()
    }

    override suspend fun deleteTask(todoTaskEntity: TodoTaskEntity) {
        val toDoTaskModel = domainToDataMapper.map(todoTaskEntity)

        toDoDao.deleteTask(toDoTaskModel)
    }

    override suspend fun updateTask(todoTaskEntity: TodoTaskEntity) {
        val todoTaskModel = domainToDataMapper.map(todoTaskEntity)

        toDoDao.updateTask(todoTaskModel)
    }

    override fun fetchSelectedTask(taskId: Int): Flow<TodoTaskEntity> {
        return toDoDao.getSelectedTask(taskId)
            .map { todoTaskModel ->
                dataToDomainMapper.map(todoTaskModel)
            }
    }

    override fun searchDatabase(searchQuery: String): Flow<List<TodoTaskEntity>> {
        return toDoDao.searchDatabase(searchQuery)
            .map { listOfTodoTaskModel ->
                listOfTodoTaskModel.map { toDoTaskModel ->
                    dataToDomainMapper.map(toDoTaskModel)
                }
            }
    }

    override fun sortByHighPriority(): Flow<List<TodoTaskEntity>> {
        return toDoDao.sortByHighPriority()
            .map { listOfTodoTaskModel ->
                listOfTodoTaskModel.map { toDoTaskModel ->
                    dataToDomainMapper.map(toDoTaskModel)
                }
            }
    }

    override fun sortByLowPriority(): Flow<List<TodoTaskEntity>> {
        return toDoDao.sortByLowPriority()
            .map { listOfTodoTaskModel ->
                listOfTodoTaskModel.map { toDoTaskModel ->
                    dataToDomainMapper.map(toDoTaskModel)
                }
            }
    }
}