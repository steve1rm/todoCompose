package me.androidbox.domain.usecase.imp

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.domain.repository.TaskRepository
import me.androidbox.domain.usecase.SortByHighPriorityUseCase

class SortByHighPriorityUseCaseImp(private val taskRepository: TaskRepository) : SortByHighPriorityUseCase {
    override fun execute(): Flow<List<TodoTaskEntity>> {
        return taskRepository.sortByHighPriority()
    }
}