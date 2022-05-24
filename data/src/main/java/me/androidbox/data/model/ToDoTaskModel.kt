package me.androidbox.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.androidbox.data.util.Constant

@Entity(tableName = Constant.DATABASE_TABLE)
data class ToDoTaskModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Int
)
