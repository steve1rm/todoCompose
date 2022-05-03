package me.androidbox.data.mapper

interface DomainToDataMapper<in E, out M> {
    fun map(entity: E): M
}
