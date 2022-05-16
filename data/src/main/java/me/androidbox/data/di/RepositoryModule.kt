package me.androidbox.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.androidbox.data.repository.imp.TodoRepositoryImp
import me.androidbox.domain.repository.TaskRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindTodoRepository(todoRepositoryImp: TodoRepositoryImp) : TaskRepository
}