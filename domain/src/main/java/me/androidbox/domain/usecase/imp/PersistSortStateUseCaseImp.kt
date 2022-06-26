package me.androidbox.domain.usecase.imp

import me.androidbox.domain.repository.DataStoreRepository
import me.androidbox.domain.usecase.PersistSortStateUseCase

class PersistSortStateUseCaseImp(private val dataStoreRepository: DataStoreRepository) :
    PersistSortStateUseCase {

    override suspend fun execute(priority: String) {
        dataStoreRepository.persistSortState(priority)
    }
}
