package me.androidbox.data.mapper.imp

import me.androidbox.data.mapper.DomainToDataMapper
import me.androidbox.data.model.ToDoTaskModel
import me.androidbox.domain.entity.TodoTaskEntity
import javax.inject.Inject

class DomainToDataMapperImp @Inject constructor() : DomainToDataMapper<TodoTaskEntity, ToDoTaskModel> {
    override fun map(entity: TodoTaskEntity): ToDoTaskModel {
        return ToDoTaskModel(
            entity.id,
            entity.title,
            entity.description,
            entity.priority
        )
    }
}