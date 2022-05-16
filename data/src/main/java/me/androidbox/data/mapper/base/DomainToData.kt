package me.androidbox.data.mapper.base

interface DomainToData<in E, out M> {
    fun map(entity: E): M
}
