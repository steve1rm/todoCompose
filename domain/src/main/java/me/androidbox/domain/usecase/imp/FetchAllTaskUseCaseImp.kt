package me.androidbox.domain.usecase.imp

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.domain.repository.TaskRepository
import me.androidbox.domain.usecase.FetchAllTaskUseCase
import javax.inject.Inject

class FetchAllTaskUseCaseImp @Inject constructor(private val taskRepository: TaskRepository) : FetchAllTaskUseCase {
    override fun execute(): Flow<List<TodoTaskEntity>> {
        return taskRepository.fetchAllTask()
    }
}
