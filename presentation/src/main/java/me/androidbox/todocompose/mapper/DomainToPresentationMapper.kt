package me.androidbox.todocompose.mapper

interface DomainToPresentationMapper<in E, out M> {
    fun map(entity: E): M
}
