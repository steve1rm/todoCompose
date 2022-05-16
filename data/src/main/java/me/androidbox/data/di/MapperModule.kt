package me.androidbox.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.androidbox.data.mapper.DataToDomainMapper
import me.androidbox.data.mapper.DomainToDataMapper
import me.androidbox.data.mapper.imp.DataToDomainMapperImp
import me.androidbox.data.mapper.imp.DomainToDataMapperImp
import me.androidbox.data.model.ToDoTaskModel
import me.androidbox.domain.entity.TodoTaskEntity

@Module
@InstallIn(SingletonComponent::class)
interface MapperModule {
    @Binds
    fun bindDomainToDataMapper(domainToDataMapperImp: DomainToDataMapperImp)
            : DomainToDataMapper<TodoTaskEntity, ToDoTaskModel>

    @Binds
    fun bindDataToDomainMapper(dataToDomainMapperImp: DataToDomainMapperImp)
            : DataToDomainMapper
}