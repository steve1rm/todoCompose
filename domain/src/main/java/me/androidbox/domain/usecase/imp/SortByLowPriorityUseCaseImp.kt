package me.androidbox.domain.usecase.imp

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.domain.repository.TaskRepository
import me.androidbox.domain.usecase.SortByLowPriorityUseCase

class SortByLowPriorityUseCaseImp(private val taskRepository: TaskRepository) :
    SortByLowPriorityUseCase {
    override fun execute(): Flow<List<TodoTaskEntity>> {
        return taskRepository.sortByLowPriority()
    }
}