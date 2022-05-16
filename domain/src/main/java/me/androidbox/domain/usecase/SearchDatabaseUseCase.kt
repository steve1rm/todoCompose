package me.androidbox.domain.usecase

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.entity.TodoTaskEntity

interface SearchDatabaseUseCase {
    fun execute(searchQuery: String): Flow<List<TodoTaskEntity>>
}
