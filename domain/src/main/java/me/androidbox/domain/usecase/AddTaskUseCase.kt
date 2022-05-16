package me.androidbox.domain.usecase

import me.androidbox.domain.entity.TodoTaskEntity

interface AddTaskUseCase {
    suspend fun execute(todoTaskEntity: TodoTaskEntity)
}
