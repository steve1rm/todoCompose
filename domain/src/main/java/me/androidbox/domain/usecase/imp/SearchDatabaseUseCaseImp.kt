package me.androidbox.domain.usecase.imp

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.domain.repository.TaskRepository
import me.androidbox.domain.usecase.SearchDatabaseUseCase

class SearchDatabaseUseCaseImp(private val taskRepository: TaskRepository) : SearchDatabaseUseCase {
    override fun execute(searchQuery: String): Flow<List<TodoTaskEntity>> {
        return taskRepository.searchDatabase(searchQuery)
    }
}