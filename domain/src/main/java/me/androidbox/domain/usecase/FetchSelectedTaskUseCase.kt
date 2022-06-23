package me.androidbox.domain.usecase

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.entity.TodoTaskEntity

interface FetchSelectedTaskUseCase {
    fun execute(taskId: Int): Flow<TodoTaskEntity?>
}
