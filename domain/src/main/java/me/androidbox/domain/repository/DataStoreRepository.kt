package me.androidbox.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun persistSortState(priority: String)
    fun readSortState(): Flow<String>
}