package me.androidbox.todocompose.mapper.imp

import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.todocompose.mapper.PresentationToDomainMapper
import me.androidbox.todocompose.model.TodoTask
import javax.inject.Inject

class PresentationToDomainMapperImp @Inject constructor() : PresentationToDomainMapper<TodoTask, TodoTaskEntity> {
    override fun map(model: TodoTask): TodoTaskEntity {
        return TodoTaskEntity(
            id = model.id,
            title = model.title,
            description = model.description,
            priority = model.priority.ordinal
        )
    }
}