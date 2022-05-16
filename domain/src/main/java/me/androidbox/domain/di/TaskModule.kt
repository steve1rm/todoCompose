package me.androidbox.domain.di

import dagger.Module
import dagger.Provides
import me.androidbox.domain.repository.TaskRepository
import me.androidbox.domain.usecase.*
import me.androidbox.domain.usecase.imp.*

@Module
interface TaskModule {

    companion object {

        @Provides
        fun provideAllTaskUseCaseImp(taskRepository: TaskRepository): FetchAllTaskUseCase {
            return FetchAllTaskUseCaseImp(taskRepository)
        }

        @Provides
        fun provideAddTaskUseCaseImp(taskRepository: TaskRepository): AddTaskUseCase {
            return AddTaskUseCaseImp(taskRepository)
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