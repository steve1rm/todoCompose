package me.androidbox.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import me.androidbox.data.dao.ToDoDao
import me.androidbox.data.model.ToDoTask

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): ToDoDao
}