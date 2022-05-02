package me.androidbox.domain.usecase

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.entity.TodoTaskEntity

interface SortByHighPriorityUseCase {
    fun sortByHighPriority(): Flow<List<TodoTaskEntity>>
}