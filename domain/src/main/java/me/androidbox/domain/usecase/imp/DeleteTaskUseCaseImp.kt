package me.androidbox.domain.usecase.imp

import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.domain.repository.TaskRepository
import me.androidbox.domain.usecase.DeleteTaskUseCase

class DeleteTaskUseCaseImp(private val taskRepository: TaskRepository) : DeleteTaskUseCase {
    override suspend fun execute(todoTaskEntity: TodoTaskEntity) {
        taskRepository.deleteTask(todoTaskEntity)
    }
}