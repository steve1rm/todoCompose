package me.androidbox.domain.usecase

import kotlinx.coroutines.flow.Flow

interface ReadSortStateUseCase {
    fun execute(): Flow<String>
}
