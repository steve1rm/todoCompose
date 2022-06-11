package me.androidbox.domain.usecase.imp

import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.domain.repository.TaskRepository
import me.androidbox.domain.usecase.UpdateTaskUseCase
import javax.inject.Inject

class UpdateTaskUseCaseImp @Inject constructor(private val taskRepository: TaskRepository) : UpdateTaskUseCase {
    override suspend fun execute(todoTaskEntity: TodoTaskEntity) {
        taskRepository.updateTask(todoTaskEntity)
    }
}