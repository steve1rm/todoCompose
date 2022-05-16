package me.androidbox.data.mapper.imp

import me.androidbox.data.mapper.DataToDomainMapper
import me.androidbox.data.model.ToDoTaskModel
import me.androidbox.domain.entity.TodoTaskEntity
import javax.inject.Inject

class DataToDomainMapperImp @Inject constructor() : DataToDomainMapper<ToDoTaskModel, TodoTaskEntity> {
    override fun map(model: ToDoTaskModel): TodoTaskEntity {
        return TodoTaskEntity(
            model.id,
            model.title,
            model.description
        )
    }
}