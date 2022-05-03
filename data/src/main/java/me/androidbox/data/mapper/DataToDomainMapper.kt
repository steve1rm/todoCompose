package me.androidbox.data.mapper

interface DataToDomainMapper<in M, out E> {
    fun map(model: M): E
}
