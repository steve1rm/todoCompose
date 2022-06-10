package me.androidbox.todocompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.todocompose.mapper.DomainToPresentationMapper
import me.androidbox.todocompose.mapper.imp.DomainToPresentationMapperImp
import me.androidbox.todocompose.model.TodoTask

@Module
@InstallIn(SingletonComponent::class)
interface MapperModule {

    @Binds
    fun bindsDomainToPresentationMapper(domainToPresentationMapperImp: DomainToPresentationMapperImp)
            : DomainToPresentationMapper<TodoTaskEntity, TodoTask>
}
