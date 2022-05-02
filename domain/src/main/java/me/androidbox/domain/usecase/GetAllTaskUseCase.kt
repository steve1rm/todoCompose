package me.androidbox.domain.usecase

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.entity.TodoTaskEntity

interface GetAllTaskUseCase {
    fun getAllTask(): Flow<List<TodoTaskEntity>>
}