package me.androidbox.domain.usecase.imp

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.domain.repository.TaskRepository
import me.androidbox.domain.usecase.FetchSelectedTaskUseCase

class FetchSelectedTaskUseCaseImp(private val taskRepository: TaskRepository) :
    FetchSelectedTaskUseCase {
    override fun execute(taskId: Int): Flow<TodoTaskEntity> {
        return taskRepository.fetchSelectedTask(taskId)
    }
}