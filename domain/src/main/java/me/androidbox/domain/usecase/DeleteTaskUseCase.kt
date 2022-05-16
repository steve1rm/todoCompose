package me.androidbox.domain.usecase

import me.androidbox.domain.entity.TodoTaskEntity

interface DeleteTaskUseCase {
    suspend fun execute(todoTaskEntity: TodoTaskEntity)
}