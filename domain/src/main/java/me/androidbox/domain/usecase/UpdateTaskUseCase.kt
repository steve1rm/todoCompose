package me.androidbox.domain.usecase

import me.androidbox.domain.entity.TodoTaskEntity

interface UpdateTaskUseCase {
    suspend fun execute(todoTaskEntity: TodoTaskEntity)
}