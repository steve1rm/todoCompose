package me.androidbox.domain.usecase.imp

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.repository.DataStoreRepository
import me.androidbox.domain.usecase.ReadSortStateUseCase

class ReadSortStateUseCaseImp(private val dataStoreRepository: DataStoreRepository) : ReadSortStateUseCase {
    override fun execute(): Flow<String> {
        return dataStoreRepository.readSortState()
    }
}
