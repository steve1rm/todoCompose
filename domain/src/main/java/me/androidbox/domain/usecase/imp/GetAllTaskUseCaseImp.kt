package me.androidbox.domain.usecase.imp

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.domain.repository.AllTaskRepository
import me.androidbox.domain.usecase.GetAllTaskUseCase
import javax.inject.Inject

class GetAllTaskUseCaseImp @Inject constructor(private val allTaskRepository: AllTaskRepository) : GetAllTaskUseCase {
    override fun execute(): Flow<List<TodoTaskEntity>> {
        return allTaskRepository.getAllTasks()
    }
}