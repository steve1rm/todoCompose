package me.androidbox.domain.usecase.imp

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.domain.repository.TaskRepository
import me.androidbox.domain.usecase.FetchSelectedTaskUseCase
import javax.inject.Inject

class FetchSelectedTaskUseCaseImp @Inject constructor(private val taskRepository: TaskRepository) :
    FetchSelectedTaskUseCase {
    override fun execute(taskId: Int): Flow<TodoTaskEntity> {
        return taskRepository.fetchSelectedTask(taskId)
    }
}