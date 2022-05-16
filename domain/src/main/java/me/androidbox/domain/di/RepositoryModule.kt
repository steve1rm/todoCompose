package me.androidbox.domain.di

import dagger.Module
import dagger.Provides
import me.androidbox.domain.repository.AllTaskRepository
import me.androidbox.domain.usecase.GetAllTaskUseCase
import me.androidbox.domain.usecase.imp.GetAllTaskUseCaseImp

@Module
interface RepositoryModule {

    companion object {

        @Provides
        fun provideAllTaskRepository(allTaskRepository: AllTaskRepository): GetAllTaskUseCase{
            return GetAllTaskUseCaseImp(allTaskRepository)
        }
    }
}