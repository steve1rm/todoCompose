package me.androidbox.todocompose.util

import me.androidbox.domain.entity.TodoTaskEntity

sealed class RequestState<out T> {
    object Idle : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T): RequestState<T>()
    data class Failure(val error: Exception): RequestState<Nothing>()
}
