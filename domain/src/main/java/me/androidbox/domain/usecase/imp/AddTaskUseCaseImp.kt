package me.androidbox.domain.usecase.imp

import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.domain.repository.TaskRepository
import me.androidbox.domain.usecase.AddTaskUseCase
import javax.inject.Inject

class AddTaskUseCaseImp @Inject constructor(private val taskRepository: TaskRepository) : AddTaskUseCase {
    override suspend fun execute(todoTaskEntity: TodoTaskEntity) {
        taskRepository.addTask(todoTaskEntity)
    }
}