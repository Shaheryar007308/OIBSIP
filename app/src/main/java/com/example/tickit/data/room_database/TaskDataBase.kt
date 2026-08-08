package com.example.tickit.data.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TaskItem::class], version = 1)
abstract class TaskDataBase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var Instance: TaskDataBase? = null

        fun getdb(context: Context): TaskDataBase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    TaskDataBase::class.java ,
                    "tasks_database"
                ).fallbackToDestructiveMigration().build().also { Instance = it }
            }
        }
    }
}