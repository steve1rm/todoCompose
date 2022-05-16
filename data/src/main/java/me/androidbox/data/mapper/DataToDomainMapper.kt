package me.androidbox.data.mapper

import me.androidbox.data.mapper.base.DataToDomain
import me.androidbox.data.model.ToDoTaskModel
import me.androidbox.domain.entity.TodoTaskEntity

interface DataToDomainMapper : DataToDomain<ToDoTaskModel, TodoTaskEntity>
