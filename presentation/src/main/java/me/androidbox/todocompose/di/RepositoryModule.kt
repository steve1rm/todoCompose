package me.androidbox.todocompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.androidbox.domain.repository.DataStoreRepository
import me.androidbox.domain.usecase.PersistSortStateUseCase
import me.androidbox.domain.usecase.ReadSortStateUseCase
import me.androidbox.domain.usecase.imp.PersistSortStateUseCaseImp
import me.androidbox.domain.usecase.imp.ReadSortStateUseCaseImp

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providePersistSortStateUseCase(dataStoreRepository: DataStoreRepository) : PersistSortStateUseCase {
        return PersistSortStateUseCaseImp(dataStoreRepository)
    }

    @Provides
    fun provideReadSortStateUseCase(dataStoreRepository: DataStoreRepository): ReadSortStateUseCase {
        return ReadSortStateUseCaseImp(dataStoreRepository)
    }
}
