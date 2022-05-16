package me.androidbox.domain.usecase.imp

import me.androidbox.domain.repository.TaskRepository
import me.androidbox.domain.usecase.DeleteAllTaskUseCase

class DeleteAllTaskUseCaseImp(private val taskRepository: TaskRepository) : DeleteAllTaskUseCase {
    override suspend fun execute() {
        taskRepository.deleteAllTask()
    }
}