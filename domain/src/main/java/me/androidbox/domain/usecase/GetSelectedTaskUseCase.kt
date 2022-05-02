package me.androidbox.domain.usecase

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.entity.TodoTaskEntity

interface GetSelectedTaskUseCase {
    fun getSelectedTask(taskId: Int): Flow<TodoTaskEntity>
}
