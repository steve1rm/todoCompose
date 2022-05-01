package me.androidbox.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.androidbox.data.dao.ToDoDao
import me.androidbox.data.database.TodoDatabase
import me.androidbox.data.util.Constant.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseModule {

    companion object {

        @Provides
        @Singleton
        fun provideRoomDatabase(@ApplicationContext context: Context): TodoDatabase {
            return Room.databaseBuilder(context, TodoDatabase::class.java, DATABASE_NAME)
                .build()
        }

        @Provides
        @Singleton
        fun provideTodoDao(todoDatabase: TodoDatabase) : ToDoDao {
            return todoDatabase.todoDao()
        }
    }
}