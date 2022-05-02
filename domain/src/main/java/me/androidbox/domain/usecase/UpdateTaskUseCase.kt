package me.androidbox.domain.usecase

import me.androidbox.domain.entity.TodoTaskEntity

interface UpdateTaskUseCase {
    suspend fun updateTask(todoTaskEntity: TodoTaskEntity)
}