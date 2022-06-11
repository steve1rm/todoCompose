package me.androidbox.todocompose.mapper

interface PresentationToDomainMapper<in M, out E> {
    fun map(model: M): E
}
