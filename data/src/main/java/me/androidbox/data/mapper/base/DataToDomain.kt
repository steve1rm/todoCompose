package me.androidbox.data.mapper.base

interface DataToDomain<in M, out E> {
    fun map(model: M): E
}
