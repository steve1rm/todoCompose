package me.androidbox.domain.usecase

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.entity.TodoTaskEntity

interface SortByLowPriorityUseCase {
    fun sortByLowPriority(): Flow<List<TodoTaskEntity>>
}
