package me.androidbox.domain.usecase

interface PersistSortStateUseCase {
    suspend fun execute(priority: String)
}
