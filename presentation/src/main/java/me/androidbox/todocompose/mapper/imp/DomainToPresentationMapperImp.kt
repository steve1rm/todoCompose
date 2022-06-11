package me.androidbox.todocompose.mapper.imp

import me.androidbox.domain.entity.TodoTaskEntity
import me.androidbox.todocompose.mapper.DomainToPresentationMapper
import me.androidbox.todocompose.model.Priority
import me.androidbox.todocompose.model.TodoTask
import javax.inject.Inject

class DomainToPresentationMapperImp @Inject constructor() : DomainToPresentationMapper<TodoTaskEntity, TodoTask> {
    override fun map(entity: TodoTaskEntity): TodoTask {
        return TodoTask(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            priority = when(entity.priority) {
                0 -> Priority.HIGH
                1 -> Priority.MEDIUM
                2 -> Priority.LOW
                3 -> Priority.NONE
                else -> Priority.NONE
            }
        )
    }
}