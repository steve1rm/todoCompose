package me.androidbox.todocompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.androidbox.domain.repository.TaskRepository
import me.androidbox.domain.usecase.*
import me.androidbox.domain.usecase.imp.*

@Module
@InstallIn(SingletonComponent::class)
interface TaskModule {

    companion object {

        @Provides
        fun provideAllTaskUseCaseImp(taskRepository: TaskRepository): FetchAllTaskUseCase {
            return FetchAllTaskUseCaseImp(taskRepository)
        }

        @Provides
        fun fetchSelectedTaskUseCaseImp(taskRepository: TaskRepository): FetchSelectedTaskUseCase {
            return FetchSelectedTaskUseCaseImp(taskRepository)
        }

        @Provides
        fun provideAddTaskUseCaseImp(taskRepository: TaskRepository): AddTaskUseCase {
            return AddTaskUseCaseImp(taskRepository)
        }

        @Provides
        fun provideUpdateTaskUseCaseImp(taskRepository: TaskRepository): UpdateTaskUseCase {
            return UpdateTaskUseCaseImp(taskRepository)
        }

        @Provides
        fun provideDeleteAllTaskUseCaseImp(taskRepository: TaskRepository): DeleteAllTaskUseCase {
            return DeleteAllTaskUseCaseImp(taskRepository)
        }

        @Provides
        fun provideDeleteTaskUseCaseImp(taskRepository: TaskRepository): DeleteTaskUseCase {
            return DeleteTaskUseCaseImp(taskRepository)
        }

        @Provides
        fun provideSearchDatabaseUseCaseImp(taskRepository: TaskRepository): SearchDatabaseUseCase {
            return SearchDatabaseUseCaseImp(taskRepository)
        }

        @Provides
        fun provideSortByLowPriorityUseCaseImp(taskRepository: TaskRepository): SortByLowPriorityUseCase {
            return SortByLowPriorityUseCaseImp(taskRepository)
        }

        @Provides
        fun provideSortByHighPriorityUseCaseImp(taskRepository: TaskRepository): SortByHighPriorityUseCase {
            return SortByHighPriorityUseCaseImp(taskRepository)
        }
    }
}