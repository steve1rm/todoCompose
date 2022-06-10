package me.androidbox.todocompose.mapper

interface DomainToPresentationMapper<in E, out P> {
    fun map(entity: E): P
}
